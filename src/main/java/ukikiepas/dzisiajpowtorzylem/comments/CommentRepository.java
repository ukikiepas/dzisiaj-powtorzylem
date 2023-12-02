package ukikiepas.dzisiajpowtorzylem.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ukikiepas.dzisiajpowtorzylem.comments.models.Comment;
import ukikiepas.dzisiajpowtorzylem.comments.models.CommentDTO;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT new ukikiepas.dzisiajpowtorzylem.comments.models.CommentDTO(c, u.image) " +
            "FROM Comment c LEFT JOIN User u ON c.username = u.username " +
            "WHERE c.section = :section AND c.sectionParticularId = :sectionParticularId " +
            "ORDER BY c.createdAt DESC")
    List<CommentDTO> findAllCommentsWithUserImages(@Param("section") String section,
                                                   @Param("sectionParticularId") Long sectionParticularId);



}
