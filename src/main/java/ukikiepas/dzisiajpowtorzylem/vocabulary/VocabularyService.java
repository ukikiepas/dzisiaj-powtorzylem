package ukikiepas.dzisiajpowtorzylem.vocabulary;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.exception.types.UserNotFoundException;
import ukikiepas.dzisiajpowtorzylem.exception.types.WordNotFoundException;
import ukikiepas.dzisiajpowtorzylem.user.UserRepository;
import ukikiepas.dzisiajpowtorzylem.user.UserService;
import ukikiepas.dzisiajpowtorzylem.user.models.User;
import ukikiepas.dzisiajpowtorzylem.vocabulary.mappers.VocabularyMapper;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.VocabularyDto;
import ukikiepas.dzisiajpowtorzylem.vocabulary.sheduler.DailyVocabularyCache;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocabularyService {

    private final UserService userService;
    private final VocabularyRepository vocabularyRepository;
    private final UserRepository userRepository;



    public List<VocabularyDto> getAllVocabularyWithFavorites(HttpServletRequest request) {
        List<VocabularyDto> vocabularies = DailyVocabularyCache.getCurrentVocabularyDto();
        String username = userService.getUsernameFromToken(request);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        for (VocabularyDto dto : vocabularies) {
            dto.setIsFavourited(checkIfUserFavoritedWord(user.getUserId(), dto.getWordId()));
        }
        return vocabularies;
    }

    @Transactional
    public ResponseEntity<?> addFavoriteWord(HttpServletRequest request, Long wordId) {
        Vocabulary word = vocabularyRepository.findById(wordId)
                .orElseThrow(() -> new WordNotFoundException(wordId));
        User user = userRepository.findByUsername(userService.getUsernameFromToken(request))
                .orElseThrow(() -> new UserNotFoundException(userService.getUsernameFromToken(request)));
        if (user.getFavoriteWords().contains(word)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Word already in favorites");
        }

        user.getFavoriteWords().add(word);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public ResponseEntity<?> deleteFavouriteWord(HttpServletRequest request, Long wordId) {
        Vocabulary word = vocabularyRepository.findById(wordId)
                .orElseThrow(() -> new WordNotFoundException(wordId));
        User user = userRepository.findByUsername(userService.getUsernameFromToken(request))
                .orElseThrow(() -> new UserNotFoundException(userService.getUsernameFromToken(request)));


        if (!user.getFavoriteWords().contains(word)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Word not in favorites");
        }

        user.getFavoriteWords().remove(word);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Transactional
    public List<Vocabulary> selectWordsForToday() {
        return vocabularyRepository.findRandomWords(3L);
    }

    private boolean checkIfUserFavoritedWord(Long userId, Long wordId) {
        return userRepository.isWordFavoritedByUser(userId, wordId);
    }
}
