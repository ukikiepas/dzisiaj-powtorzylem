package ukikiepas.dzisiajpowtorzylem.userresults;

import org.springframework.data.jpa.repository.JpaRepository;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResultsAnswers;

import java.util.List;

public interface UserResultsAnswersRepository extends JpaRepository<UserResultsAnswers, Long> {

    List<UserResultsAnswers> findByResultId(Long resultId);

}
