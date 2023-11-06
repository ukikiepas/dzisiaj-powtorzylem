package ukikiepas.dzisiajpowtorzylem.security.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ukikiepas.dzisiajpowtorzylem.models.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
