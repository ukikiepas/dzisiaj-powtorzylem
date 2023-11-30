package ukikiepas.dzisiajpowtorzylem.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import ukikiepas.dzisiajpowtorzylem.comments.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
