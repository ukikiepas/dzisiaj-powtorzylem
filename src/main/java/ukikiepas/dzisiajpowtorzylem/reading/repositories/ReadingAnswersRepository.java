package ukikiepas.dzisiajpowtorzylem.reading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukikiepas.dzisiajpowtorzylem.reading.models.ReadingAnswer;

import java.util.List;

public interface ReadingAnswersRepository extends JpaRepository<ReadingAnswer, Long> {
    List<ReadingAnswer> findByReadingQuestionQuestionId(Long questionId);
}
