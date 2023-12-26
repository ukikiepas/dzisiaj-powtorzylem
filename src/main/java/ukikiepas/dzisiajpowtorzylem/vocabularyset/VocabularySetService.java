package ukikiepas.dzisiajpowtorzylem.vocabularyset;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.user.UserService;
import ukikiepas.dzisiajpowtorzylem.user.models.Role;
import ukikiepas.dzisiajpowtorzylem.utils.ApiResponse;
import ukikiepas.dzisiajpowtorzylem.utils.ResponseStatus;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.VocabularyDto;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.mappers.VocabularySetMapper;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySet;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySetDto;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySetViewDto;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VocabularySetService {

    private final UserService userService;
    private final VocabularySetRepository vocabularySetRepository;
    private final VocabularySetMapper vocabularySetMapper;

    public ResponseEntity<ApiResponse<Long>> createSet(VocabularySetDto vocabSetDto, HttpServletRequest request) {
        try {
            VocabularySet createdSet = VocabularySet.builder()
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
            return ResponseEntity.ok(new ApiResponse<>(ResponseStatus.SUCCESS, "Zestaw utworzony prawidłowo", createdSet.getSetId()));
        } catch (DataAccessException e) {
            //logger.error("Błąd podczas zapisu zestawu: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ResponseStatus.FAILURE, "Błąd podczas zapisu zestawu"));
        }
    }

    public ResponseEntity<ApiResponse<VocabularySetDto>> updateSet(VocabularySetDto vocabSetDto) {
        try {
            return vocabularySetRepository.findById(vocabSetDto.getId())
                    .map(existingSet -> {
                        // Aktualizacja danych
                        existingSet.setTitle(vocabSetDto.getTitle());
                        existingSet.setDescription(vocabSetDto.getDescription());
                        existingSet.setCategory(vocabSetDto.getCategory());
                        existingSet.setIsPublic(vocabSetDto.getIsPublic());
                        updateVocabularies(existingSet, vocabSetDto.getVocabularyDtos());

                        vocabularySetRepository.save(existingSet);

                        VocabularySetDto updatedSetDto = vocabularySetMapper.convertToDto(existingSet, existingSet.getVocabularies());
                        return ResponseEntity.ok(new ApiResponse<>(ResponseStatus.SUCCESS, "Zestaw zaktualizowany poprawnie", updatedSetDto));
                    })
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ApiResponse<>(ResponseStatus.FAILURE, "Zestaw nie znaleziony", null)));
        } catch (DataAccessException e) {
            //logger.error("Błąd podczas aktualizacji zestawu: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ResponseStatus.FAILURE, "Błąd podczas aktualizacji zestawu", null));
        }
    }

    public ResponseEntity<ApiResponse<List<VocabularySetDto>>> getAllUserSets(HttpServletRequest request) {

        String username = userService.getUsernameFromToken(request);
        List<VocabularySet> sets = vocabularySetRepository.findAllByCreatorWithVocabularies(username);

        List<VocabularySetDto> setDtos = sets.stream()
                .map(set -> {
                    Set<Vocabulary> vocabularies = vocabularySetRepository.findByVocabularySetId(set.getSetId());
                    // Przekształć VocabularySet do VocabularySetDto, uwzględniając słówka
                    return vocabularySetMapper.convertToDto(set, vocabularies);
                })
                .toList();

        return new ResponseEntity<>(new ApiResponse<>(ResponseStatus.SUCCESS, "", setDtos), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse<VocabularySetDto>> getSet(Long setId){
        return vocabularySetRepository.findById(setId)
                .map(vocabularySet -> {
                    Set<Vocabulary> vocabularies = vocabularySetRepository.findByVocabularySetId(setId);
                    VocabularySetDto setDto = vocabularySetMapper.convertToDto(vocabularySet, vocabularies);
                    return new ResponseEntity<>(new ApiResponse<>(ResponseStatus.SUCCESS, "Zestaw znaleziony.", setDto), HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(new ApiResponse<>(ResponseStatus.FAILURE, "Zestaw o podanym ID nie został znaleziony."), HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<ApiResponse<Page<VocabularySetViewDto>>> findSets(String searchTerm, String category, int page, int size, HttpServletRequest request) {
        String currentUsername = userService.getUsernameFromToken(request);
        Page<VocabularySet> sets = vocabularySetRepository.findWithFilters(
                searchTerm,
                category,
                PageRequest.of(page, size)
        );

        List<VocabularySetViewDto> filteredSets = sets.stream()
                .filter(set -> !set.getCreator().equals(currentUsername))
                .map(vocabularySetMapper::convertToViewDto)
                .toList();

        Page<VocabularySetViewDto> pageOfFilteredSets = new PageImpl<>(filteredSets, sets.getPageable(), sets.getTotalElements());

        return ResponseEntity.ok(new ApiResponse<>(ResponseStatus.SUCCESS, "Zestawy znalezione", pageOfFilteredSets));
    }



    public ResponseEntity<ApiResponse<Void>> addToFavourite(Long setId, HttpServletRequest request) {
        Optional<VocabularySet> vocabularySetOptional = vocabularySetRepository.findById(setId);
        if (vocabularySetOptional.isPresent()) {
            Set<Vocabulary> vocabularies = vocabularySetRepository.findByVocabularySetId(setId);
            VocabularySet originalSet = vocabularySetOptional.get();
            String newCreator = userService.getUsernameFromToken(request);

            VocabularySet newSet = vocabularySetMapper.copySetWithNewCreator(originalSet, vocabularies ,newCreator);
            vocabularySetRepository.save(newSet);

            return ResponseEntity.ok(new ApiResponse<>(ResponseStatus.SUCCESS, "Zestaw został dodany do ulubionych."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(ResponseStatus.FAILURE, "Zestaw o podanym ID nie został znaleziony."));
        }
    }


    private void updateVocabularies(VocabularySet exsisingSet, Set<VocabularyDto> vocabDtos) {
        Set<Long> vocabIds = vocabDtos.stream()
                .map(VocabularyDto::getWordId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        List<Vocabulary> vocabulariesToRemove = exsisingSet.getVocabularies().stream()
                .filter(vocab -> !vocabIds.contains(vocab.getWordId()))
                .toList();
        vocabulariesToRemove.forEach(exsisingSet.getVocabularies()::remove);

        vocabDtos.forEach(vocabDto -> {
            if (vocabDto.getWordId() != null) {
                exsisingSet.getVocabularies().stream()
                        .filter(vocab -> vocab.getWordId().equals(vocabDto.getWordId()))
                        .findFirst()
                        .ifPresent(vocab -> updateVocabulary(vocab, vocabDto));
            } else {
                Vocabulary newVocabulary = vocabularySetMapper.createVocabularyFromDto(vocabDto);
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
