package ukikiepas.dzisiajpowtorzylem.userresults;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.userresults.mappers.UserResultsMapper;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResultDto;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResults;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResultsAnswers;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.VocabularySetRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserResultsService {

    private final UserResultsRepository userResultsRepository;
    private final UserResultsAnswersRepository userResultsAnswersRepository;
    private final UserResultsMapper userResultsMapper;
    private final VocabularySetRepository vocabularySetRepository;

    @Transactional
    public ResponseEntity<?> saveUserResults(UserResultDto userResultDto) {
        UserResults userResults = userResultsMapper.mapDtoToUserResults(userResultDto);
        userResults = userResultsRepository.save(userResults);

        List<UserResultsAnswers> answers = userResultsMapper.mapDtoToUserResultsAnswers(userResultDto, userResults.getResultId());
        userResultsAnswersRepository.saveAll(answers);

        vocabularySetRepository.updateLastReviewed(userResultDto.getSetId(), LocalDate.now());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
