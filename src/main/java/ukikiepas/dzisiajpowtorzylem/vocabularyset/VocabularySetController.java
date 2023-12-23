package ukikiepas.dzisiajpowtorzylem.vocabularyset;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySetDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/vocabulary-set")
public class VocabularySetController {

    private final VocabularySetService vocabularySetService;

    //create
    @Transactional
    @PostMapping("/create")
    public ResponseEntity<?> createSet (@RequestBody VocabularySetDto vocabSetDto, HttpServletRequest request){
        return vocabularySetService.createSet(vocabSetDto, request);
    }

    @Transactional
    @PatchMapping("/update")
    public ResponseEntity<?> updateSet (@RequestBody VocabularySetDto vocabSetDto){
        return vocabularySetService.updateSet(vocabSetDto);
    }

    //find

    //getAllUser
    @Transactional
    @GetMapping("/all-user")
    public ResponseEntity<List<VocabularySetDto>> getAllUserSets(HttpServletRequest request){
        return vocabularySetService.getAllUserSets(request);
    }

    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<VocabularySetDto> getAllUserSets(@PathVariable("id") Long setId){
        return vocabularySetService.getSet(setId);
    }



}

