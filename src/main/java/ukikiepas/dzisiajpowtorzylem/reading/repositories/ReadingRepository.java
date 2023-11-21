package ukikiepas.dzisiajpowtorzylem.reading.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ukikiepas.dzisiajpowtorzylem.reading.models.Reading;

import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

    Reading findByReadingId(Long readingId);

    List<Reading> findAllByLevel(String level);

}
