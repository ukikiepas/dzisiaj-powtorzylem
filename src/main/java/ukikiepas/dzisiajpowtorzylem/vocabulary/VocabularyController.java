package ukikiepas.dzisiajpowtorzylem.vocabulary;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.VocabularyDto;
import ukikiepas.dzisiajpowtorzylem.vocabulary.sheduler.DailyVocabularyCache;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/vocabulary")
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @GetMapping
    public List<VocabularyDto> getDailyVocabulary(HttpServletRequest request){
        return vocabularyService.getAllVocabularyWithFavorites(request);
    }

    @PostMapping("/add-fav")
    public ResponseEntity<?> addFavoriteWord(HttpServletRequest request, @RequestParam Long wordId) {
        return vocabularyService.addFavoriteWord(request, wordId);
    }

    @DeleteMapping("/delete-fav")
    public ResponseEntity<?> deleteFavoriteWord(HttpServletRequest request, @RequestParam Long wordId) {
        return vocabularyService.deleteFavouriteWord(request, wordId);
    }
}

