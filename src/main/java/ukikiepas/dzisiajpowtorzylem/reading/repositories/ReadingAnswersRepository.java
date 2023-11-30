package ukikiepas.dzisiajpowtorzylem.reading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import ukikiepas.dzisiajpowtorzylem.reading.models.ReadingAnswer;

public interface ReadingAnswersRepository extends JpaRepository<ReadingAnswer, Long> {
    List<ReadingAnswer> findByReadingQuestionQuestionId(Long questionId);
}
