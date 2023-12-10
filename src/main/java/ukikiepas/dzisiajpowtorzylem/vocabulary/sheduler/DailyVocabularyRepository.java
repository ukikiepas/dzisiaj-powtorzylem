package ukikiepas.dzisiajpowtorzylem.vocabulary.sheduler;

import org.springframework.data.jpa.repository.JpaRepository;
import ukikiepas.dzisiajpowtorzylem.vocabulary.sheduler.models.DailyVocabulary;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyVocabularyRepository extends JpaRepository<DailyVocabulary, Long> {
    Optional<DailyVocabulary> findByDate(LocalDate date);
}

