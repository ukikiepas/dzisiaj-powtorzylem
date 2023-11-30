package ukikiepas.dzisiajpowtorzylem.comments;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.comments.models.Comment;
import ukikiepas.dzisiajpowtorzylem.user.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository repository;
    private final UserService userService;

    public List<Comment> getAllComments(){
        return repository.findAll();
    }

    public void addNewComment(Comment receivedComment, HttpServletRequest request){
        receivedComment.setCreatedAt(LocalDateTime.now());
        receivedComment.setUsername(userService.getUsernameFromToken(request));
        receivedComment.setUserId(null);
        repository.save(receivedComment);
    }

    public void editComment(){

    }

}
