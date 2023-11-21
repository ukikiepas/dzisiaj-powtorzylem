package ukikiepas.dzisiajpowtorzylem.reading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukikiepas.dzisiajpowtorzylem.reading.models.ReadingQuestion;

import java.util.List;

public interface ReadingQuestionsRepository extends JpaRepository<ReadingQuestion, Long> {

    List<ReadingQuestion> findByReadingReadingId(Long id);

}
