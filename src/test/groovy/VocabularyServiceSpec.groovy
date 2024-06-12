import spock.lang.Specification
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import ukikiepas.dzisiajpowtorzylem.vocabulary.VocabularyService
import ukikiepas.dzisiajpowtorzylem.vocabulary.VocabularyRepository
import ukikiepas.dzisiajpowtorzylem.user.UserRepository
import ukikiepas.dzisiajpowtorzylem.user.UserService
import ukikiepas.dzisiajpowtorzylem.exception.types.WordNotFoundException
import ukikiepas.dzisiajpowtorzylem.user.models.User
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary
import jakarta.servlet.http.HttpServletRequest

import java.time.LocalDate

class VocabularyServiceSpec extends Specification {

    VocabularyRepository vocabularyRepository
    UserRepository userRepository
    UserService userService
    HttpServletRequest request
    VocabularyService service

    def setup() {
        vocabularyRepository = Mock(VocabularyRepository)
        userRepository = Mock(UserRepository)
        userService = Mock(UserService)
        request = Mock(HttpServletRequest)
        service = new VocabularyService(userService, vocabularyRepository, userRepository)
    }

    def "add favorite word should add word and return response entity"() {
        given:
        Vocabulary word = new Vocabulary(wordId: 1L)
        User user = User.builder()
                .userId(1L)
                .username("testUser")
                .firstname("Test")
                .lastname("User")
                .email("test@example.com")
                .password("password")
                .enabled(true)
                .nonLocked(true)
                .creationDate(LocalDate.now())
                .favoriteWords(new HashSet<>())
                .build()
        String username = "testUser"
        Long wordId = 1L

        userService.getUsernameFromToken(request) >> username
        userRepository.findByUsername(username) >> Optional.of(user)
        vocabularyRepository.findById(wordId) >> Optional.of(word)

        when:
        ResponseEntity<?> response = service.addFavoriteWord(request, wordId)

        then:
        1 * userRepository.save(user)
        response.getStatusCode() == HttpStatus.OK
    }

    def "add favorite word should throw WordNotFoundException for invalid wordId"() {
        given:
        String username = "testUser"
        Long invalidWordId = 99L

        userService.getUsernameFromToken(request) >> username
        userRepository.findByUsername(username) >> Optional.of(User.builder().userId(1L).favoriteWords(new HashSet<>()).build())
        vocabularyRepository.findById(invalidWordId) >> Optional.empty()

        when:
        service.addFavoriteWord(request, invalidWordId)

        then:
        thrown(WordNotFoundException)
    }

    def "delete favorite word should remove word from favorites and return OK status"() {
        given:
        Vocabulary word = new Vocabulary(wordId: 1L)
        User user = User.builder()
                .userId(1L)
                .favoriteWords(new HashSet<>(Arrays.asList(word)))
                .build()
        Long wordId = 1L

        userService.getUsernameFromToken(request) >> "testUser"
        userRepository.findByUsername("testUser") >> Optional.of(user)
        vocabularyRepository.findById(wordId) >> Optional.of(word)

        when:
        ResponseEntity<?> response = service.deleteFavouriteWord(request, wordId)

        then:
        1 * userRepository.save(user)
        !user.getFavoriteWords().contains(word)
        response.getStatusCode() == HttpStatus.OK
    }

    def "delete favorite word should return NOT FOUND status for non-favorite word"() {
        given:
        Vocabulary word = new Vocabulary(wordId: 1L)
        User user = User.builder()
                .userId(1L)
                .favoriteWords(new HashSet<>())
                .build()
        Long wordId = 1L

        userService.getUsernameFromToken(request) >> "testUser"
        userRepository.findByUsername("testUser") >> Optional.of(user)
        vocabularyRepository.findById(wordId) >> Optional.of(word)

        when:
        ResponseEntity<?> response = service.deleteFavouriteWord(request, wordId)

        then:
        response.getStatusCode() == HttpStatus.NOT_FOUND
    }


}
