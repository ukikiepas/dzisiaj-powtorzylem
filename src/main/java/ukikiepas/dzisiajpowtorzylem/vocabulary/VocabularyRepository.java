package ukikiepas.dzisiajpowtorzylem.vocabulary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;

import java.util.List;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
    @Query(value = "SELECT * FROM vocabulary WHERE is_approved = true ORDER BY RANDOM() LIMIT :amount", nativeQuery = true)
    List<Vocabulary> findRandomWords(@Param("amount") Long amount);


}
