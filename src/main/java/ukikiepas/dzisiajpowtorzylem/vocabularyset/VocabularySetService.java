package ukikiepas.dzisiajpowtorzylem.vocabularyset;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.user.UserService;
import ukikiepas.dzisiajpowtorzylem.user.models.Role;
import ukikiepas.dzisiajpowtorzylem.vocabulary.VocabularyRepository;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.VocabularyDto;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.mappers.VocabularySetMapper;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySet;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySetDto;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VocabularySetService {

    private final UserService userService;
    private final VocabularySetRepository vocabularySetRepository;
    private final VocabularyRepository vocabularyRepository;
    private final VocabularySetMapper vocabularySetMapper;

    public ResponseEntity<?> createSet(VocabularySetDto vocabSetDto, HttpServletRequest request) {
        VocabularySet createdSet =  VocabularySet.builder()
                .title(vocabSetDto.getTitle())
                .description(vocabSetDto.getDescription())
                .creator(userService.getUsernameFromToken(request))
                .category(vocabSetDto.getCategory())
                .isPublic(vocabSetDto.getIsPublic())
                .isActive(false)
                .lastReviewed(null)
                .isCreatedByAdmin(userService.getRoleFromToken(request) == Role.ADMIN)
                .creationDate(LocalDate.now())
                .vocabularies(vocabularySetMapper.convertDtoToVocabulary(vocabSetDto.getVocabularyDtos()))
                .build();

        vocabularySetRepository.save(createdSet);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateSet(VocabularySetDto vocabSetDto) {
        Optional<VocabularySet> existingSetOpt = vocabularySetRepository.findById(vocabSetDto.getId());
        if (existingSetOpt.isPresent()) {
            VocabularySet existingSet = existingSetOpt.get();

            existingSet.setTitle(vocabSetDto.getTitle());
            existingSet.setDescription(vocabSetDto.getDescription());
            existingSet.setCategory(vocabSetDto.getCategory());
            existingSet.setIsPublic(vocabSetDto.getIsPublic());
            existingSet.setVocabularies(vocabularySetRepository.findByVocabularySetId(vocabSetDto.getId()));

            updateVocabularies(existingSet, vocabSetDto.getVocabularyDtos());
            vocabularySetRepository.save(existingSet);
            return new ResponseEntity<>("Zestaw zaktualizowany poprawnie", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Zestaw nie znaleziony", HttpStatus.NOT_FOUND);
        }
    }



    public ResponseEntity<List<VocabularySetDto>> getAllUserSets(HttpServletRequest request) {

        String username = userService.getUsernameFromToken(request);
        List<VocabularySet> sets = vocabularySetRepository.findAllByCreatorWithVocabularies(username);

        List<VocabularySetDto> setDtos = sets.stream()
                .map(set -> {
                    Set<Vocabulary> vocabularies = vocabularySetRepository.findByVocabularySetId(set.getSetId());
                    // Przekształć VocabularySet do VocabularySetDto, uwzględniając słówka
                    return vocabularySetMapper.convertToDto(set, vocabularies);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(setDtos);
    }

    public ResponseEntity<VocabularySetDto> getSet(Long setId){
        Optional<VocabularySet> vocabularySet = vocabularySetRepository.findById(setId);
        Set<Vocabulary> vocabularies = vocabularySetRepository.findByVocabularySetId(setId);
        return ResponseEntity.ok(vocabularySetMapper.convertToDto(vocabularySet.get(), vocabularies));
    }

    private void updateVocabularies(VocabularySet exsisingSet, Set<VocabularyDto> vocabDtos) {
        Set<Long> vocabIds = vocabDtos.stream()
                .map(VocabularyDto::getWordId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        // Usuń słówka, które nie są już w zestawie
        List<Vocabulary> vocabulariesToRemove = exsisingSet.getVocabularies().stream()
                .filter(vocab -> !vocabIds.contains(vocab.getWordId()))
                .collect(Collectors.toList());
        exsisingSet.getVocabularies().removeAll(vocabulariesToRemove);

        vocabDtos.forEach(vocabDto -> {
            if (vocabDto.getWordId() != null) {
                exsisingSet.getVocabularies().stream()
                        .filter(vocab -> vocab.getWordId().equals(vocabDto.getWordId()))
                        .findFirst()
                        .ifPresent(vocab -> updateVocabulary(vocab, vocabDto));
            } else {
                Vocabulary newVocabulary = vocabularySetMapper.convertToVocabulary(vocabDto);
                exsisingSet.getVocabularies().add(newVocabulary);
            }
        });
    }


    private void updateVocabulary(Vocabulary vocabulary, VocabularyDto vocabDto) {
        vocabulary.setWord(vocabDto.getWord());
        vocabulary.setTranslation(vocabDto.getTranslation());
        vocabulary.setDefinition(vocabDto.getDefinition());
        vocabulary.setImageLocation(vocabDto.getImageLocation());
    }





}
