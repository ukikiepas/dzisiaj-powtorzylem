package ukikiepas.dzisiajpowtorzylem.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ukikiepas.dzisiajpowtorzylem.user.models.User;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("UPDATE User u SET u.image = :image WHERE u.username = :username")
    int updateImageByUsername(String username, String image);

    @Query(value = "SELECT EXISTS (" +
            "SELECT 1 FROM user_vocabulary uv " +
            "JOIN users u ON u.user_id = uv.user_id " +
            "JOIN vocabulary v ON v.word_id = uv.word_id " +
            "WHERE u.user_id = :userId AND v.word_id = :wordId)", nativeQuery = true)
    boolean isWordFavoritedByUser(@Param("userId") Long userId, @Param("wordId") Long wordId);




}
