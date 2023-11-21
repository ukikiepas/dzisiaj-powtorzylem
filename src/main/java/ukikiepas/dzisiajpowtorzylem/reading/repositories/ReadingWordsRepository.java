package ukikiepas.dzisiajpowtorzylem.reading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukikiepas.dzisiajpowtorzylem.reading.models.ReadingWord;

import java.util.List;

public interface ReadingWordsRepository extends JpaRepository<ReadingWord, Long> {

    List<ReadingWord> findByReadingReadingId(Long id);

}
