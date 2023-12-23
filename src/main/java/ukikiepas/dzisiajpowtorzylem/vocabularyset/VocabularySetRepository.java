package ukikiepas.dzisiajpowtorzylem.vocabularyset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySet;

import java.util.List;
import java.util.Set;

public interface VocabularySetRepository extends JpaRepository<VocabularySet, Long> {

    @Query("SELECT DISTINCT vs FROM VocabularySet vs " +
            "LEFT JOIN FETCH vs.vocabularies " +
            "WHERE vs.creator = :creator")
    List<VocabularySet> findAllByCreatorWithVocabularies(@Param("creator") String creator);

    @Query("SELECT v FROM Vocabulary v " +
            "JOIN v.vocabularySets vs " +
            "WHERE vs.setId = :setId")
    Set<Vocabulary> findByVocabularySetId(@Param("setId") Long setId);

}



