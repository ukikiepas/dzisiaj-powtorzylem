package ukikiepas.dzisiajpowtorzylem.comments;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.comments.models.Comment;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/comments")
public class CommentController {

    private final CommentService service;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
         return ResponseEntity.ok(service.getAllComments());
    }

    @PostMapping
    public void addNewComment(@RequestBody Comment receivedComment, HttpServletRequest request) {
        service.addNewComment(receivedComment, request);
    }

    @PatchMapping
    public void editComment(){

    }

}
