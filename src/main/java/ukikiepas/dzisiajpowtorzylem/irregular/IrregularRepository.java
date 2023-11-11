package ukikiepas.dzisiajpowtorzylem.irregular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ukikiepas.dzisiajpowtorzylem.irregular.models.IrregularVerb;

import java.util.List;

public interface IrregularRepository extends JpaRepository<IrregularVerb, Long> {

    //todo poprawic zeby brac tylko te kolumndy co chce dla wydajnosci
    @Query(value = "SELECT * FROM irregular_verbs WHERE level IN :levels ORDER BY RANDOM() LIMIT :count", nativeQuery = true)
    List<IrregularVerb> findRandomVerbsByLevel(@Param("levels") List<String> levels, @Param("count") int count);

}
