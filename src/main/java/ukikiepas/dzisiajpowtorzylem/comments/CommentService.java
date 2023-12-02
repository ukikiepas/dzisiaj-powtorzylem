package ukikiepas.dzisiajpowtorzylem.comments;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.comments.models.Comment;
import ukikiepas.dzisiajpowtorzylem.comments.models.CommentDTO;
import ukikiepas.dzisiajpowtorzylem.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserService userService;
    private final CommentRepository repository;

    public List<CommentDTO> getAllCommentsWithImages(String section, Long sectionParticularId) {
        return repository.findAllCommentsWithUserImages(section, sectionParticularId);
    }

    public Comment addNewComment(Comment receivedComment, HttpServletRequest request){
        receivedComment.setCreatedAt(LocalDateTime.now());
        receivedComment.setUsername(userService.getUsernameFromToken(request));
        repository.save(receivedComment);
        return receivedComment;
    }

    @Transactional
    public void editComment(Long commentId, String body) {
        Comment comment = repository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        comment.setBody(body);
    }

    public void deleteComment(Long commentId){
        repository.deleteById(commentId);
    }


}
