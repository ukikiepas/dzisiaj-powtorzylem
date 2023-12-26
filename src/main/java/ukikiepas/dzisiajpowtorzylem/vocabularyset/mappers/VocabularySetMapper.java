package ukikiepas.dzisiajpowtorzylem.vocabularyset.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.vocabulary.VocabularyRepository;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.VocabularyDto;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySet;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySetDto;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySetViewDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VocabularySetMapper {

    private final VocabularyRepository vocabularyRepository;

    public Set<Vocabulary> convertDtoToVocabulary(Set<VocabularyDto> vocabularyDtos) {
        return vocabularyDtos.stream()
                .map(this::createOrUpdateVocabulary)
                .collect(Collectors.toSet());
    }

    public VocabularySetDto convertToDto(VocabularySet set, Set<Vocabulary> vocabularies) {
        Set<VocabularyDto> vocabularyDtos = vocabularies.stream()
                .map(this::convertVocabularyToDto)
                .collect(Collectors.toSet());

        return VocabularySetDto.builder()
                .id(set.getSetId())
                .title(set.getTitle())
                .description(set.getDescription())
                .creator(set.getCreator())
                .category(set.getCategory())
                .isPublic(set.getIsPublic())
                .isActive(set.getIsActive())
                .lastReviewed(set.getLastReviewed())
                .isCreatedByAdmin(set.getIsCreatedByAdmin())
                .creationDate(set.getCreationDate())
                .vocabularyDtos(vocabularyDtos)
                .build();
    }


    public Vocabulary createVocabularyFromDto(VocabularyDto vocabDto) {
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setWord(vocabDto.getWord());
        vocabulary.setTranslation(vocabDto.getTranslation());
        vocabulary.setDefinition(vocabDto.getDefinition());
        vocabulary.setImageLocation(vocabDto.getImageLocation());
        return vocabulary;
    }

    public VocabularySetViewDto convertToViewDto(VocabularySet set) {
        return VocabularySetViewDto.builder()
                .id(set.getSetId())
                .title(set.getTitle())
                .description(set.getDescription())
                .creator(set.getCreator())
                .creationDate(set.getCreationDate())
                .build();
    }

    public VocabularySet copySetWithNewCreator(VocabularySet originalSet, Set<Vocabulary> vocabularySet, String newCreator) {
        Set<Vocabulary> vocabularies = new HashSet<>(originalSet.getVocabularies());

        VocabularySet newSet = new VocabularySet();
        newSet.setTitle(originalSet.getTitle());
        newSet.setDescription(originalSet.getDescription());
        newSet.setCategory(originalSet.getCategory());
        newSet.setIsPublic(originalSet.getIsPublic());
        newSet.setIsActive(originalSet.getIsActive());
        newSet.setIsCreatedByAdmin(false);
        newSet.setCreationDate(LocalDate.now());
        newSet.setVocabularies(vocabularies);
        newSet.setCreator(newCreator);

        return newSet;
    }

    private VocabularyDto convertVocabularyToDto(Vocabulary vocabulary) {
        return VocabularyDto.builder()
                .wordId(vocabulary.getWordId())
                .word(vocabulary.getWord())
                .translation(vocabulary.getTranslation())
                .definition(vocabulary.getDefinition())
                .level(vocabulary.getLevel())
                .imageLocation(vocabulary.getImageLocation())
                .isFavourited(false)
                .build();
    }

    private Vocabulary createOrUpdateVocabulary(VocabularyDto vocabDto) {
        return vocabDto.getWordId() != null
                ? vocabularyRepository.findById(vocabDto.getWordId())
                .orElseGet(() -> createVocabularyFromDto(vocabDto))
                : createVocabularyFromDto(vocabDto);
    }
}
