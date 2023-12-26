package ukikiepas.dzisiajpowtorzylem.vocabularyset;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.utils.ApiResponse;
import ukikiepas.dzisiajpowtorzylem.utils.ResponseStatus;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySetDto;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySetViewDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/vocabulary-set")
public class VocabularySetController {

    private final VocabularySetService vocabularySetService;

    @Transactional
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Long>> createSet (@RequestBody VocabularySetDto vocabSetDto, HttpServletRequest request){
        return vocabularySetService.createSet(vocabSetDto, request);
    }

    @Transactional
    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<VocabularySetDto>> updateSet (@RequestBody VocabularySetDto vocabSetDto){
        return vocabularySetService.updateSet(vocabSetDto);
    }

    //find
    @GetMapping("/find")
    public ResponseEntity<ApiResponse<Page<VocabularySetViewDto>>> findSets(
            @RequestParam(value = "searchTerm", required = false) String searchTerm,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            HttpServletRequest request) {

        return vocabularySetService.findSets(searchTerm, category, page, size, request);
    }

    //getAllUser
    @Transactional
    @GetMapping("/all-user")
    public ResponseEntity<ApiResponse<List<VocabularySetDto>>> getAllUserSets(HttpServletRequest request){
        return vocabularySetService.getAllUserSets(request);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VocabularySetDto>> getAllUserSets(@PathVariable("id") Long setId){
        return vocabularySetService.getSet(setId);
    }

    //add set to fav
    @Transactional
    @PostMapping("/add-fav/{id}")
    public ResponseEntity<ApiResponse<Void>> addToFavourites(@PathVariable("id") Long setId, HttpServletRequest request) {
        return vocabularySetService.addToFavourite(setId, request);
    }



}

