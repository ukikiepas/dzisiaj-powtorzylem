package ukikiepas.dzisiajpowtorzylem.vocabularyset.mappers;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.vocabulary.VocabularyRepository;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.VocabularyDto;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySet;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySetDto;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VocabularySetMapper {

    private final VocabularyRepository vocabularyRepository;

    public Set<Vocabulary> convertDtoToVocabulary(Set<VocabularyDto> vocabularyDtos) {
        Set<Vocabulary> vocabularies = new HashSet<>();
        for (VocabularyDto dto : vocabularyDtos) {
            Vocabulary vocabulary = Vocabulary.builder()
                    .word(dto.getWord())
                    .translation(dto.getTranslation())
                    .definition(dto.getDefinition())
                    .imageLocation(dto.getImageLocation())
                    .build();
            vocabularies.add(vocabulary);
        }
        return vocabularies;
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

    public VocabularyDto convertVocabularyToDto(Vocabulary vocabulary) {
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
    public Vocabulary convertToVocabulary(VocabularyDto vocabDto) {
        Vocabulary vocabulary;
        if (vocabDto.getWordId() != null) {
            vocabulary = vocabularyRepository.findById(vocabDto.getWordId())
                    .orElse(new Vocabulary());
        } else {
            vocabulary = new Vocabulary();
        }
        vocabulary.setWord(vocabDto.getWord());
        vocabulary.setTranslation(vocabDto.getTranslation());
        // pozostałe pola
        return vocabulary;
    }


}
