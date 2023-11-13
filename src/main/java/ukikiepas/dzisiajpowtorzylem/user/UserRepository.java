package ukikiepas.dzisiajpowtorzylem.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ukikiepas.dzisiajpowtorzylem.user.models.User;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.image = :image WHERE u.username = :username")
    int updateImageByUsername(String username, String image);

}
