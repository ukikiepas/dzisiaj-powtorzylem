package ukikiepas.dzisiajpowtorzylem.userresults;

import org.springframework.data.jpa.repository.JpaRepository;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResults;

import java.time.LocalDateTime;
import java.util.List;

public interface UserResultsRepository extends JpaRepository<UserResults, Long> {

    List<UserResults> findByInsertTimeBetweenAndUsername(LocalDateTime startOfDay, LocalDateTime endOfDay, String username);


}
