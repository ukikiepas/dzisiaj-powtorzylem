package ukikiepas.dzisiajpowtorzylem.userresults;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.user.UserService;
import ukikiepas.dzisiajpowtorzylem.userresults.mappers.UserResultsMapper;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResultDto;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResults;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResultsAnswers;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.VocabularySetRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserResultsService {

    private final UserResultsRepository userResultsRepository;
    private final UserResultsAnswersRepository userResultsAnswersRepository;
    private final UserResultsMapper userResultsMapper;
    private final VocabularySetRepository vocabularySetRepository;
    private final UserService userService;

    @Transactional
    public ResponseEntity<?> saveUserResults(UserResultDto userResultDto) {
        UserResults userResults = userResultsMapper.mapDtoToUserResults(userResultDto);
        userResults = userResultsRepository.save(userResults);

        List<UserResultsAnswers> answers = userResultsMapper.mapDtoToUserResultsAnswers(userResultDto, userResults.getResultId());
        userResultsAnswersRepository.saveAll(answers);

        vocabularySetRepository.updateLastReviewed(userResultDto.getSetId(), LocalDate.now());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getResultsDay(String dateString, HttpServletRequest request) {
        try {
            LocalDate date = LocalDate.parse(dateString); //YYYY-MM-DD
            LocalDateTime startOfDay = date.atStartOfDay();
            LocalDateTime endOfDay = date.atTime(LocalTime.MAX);
            String username = userService.getUsernameFromToken(request);

            List<UserResults> userResults = userResultsRepository.findByInsertTimeBetweenAndUsername(startOfDay, endOfDay, username);

            return ResponseEntity.ok(
                    userResults.stream().map(ur -> {
                        List<UserResultsAnswers> answers = userResultsAnswersRepository.findByResultId(ur.getResultId());
                        return userResultsMapper.mergeToUserResultDto(ur, answers);
                    }).toList());

        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Niepoprawny format daty: " + dateString);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Wewnętrzny błąd serwera");
        }
    }
}













