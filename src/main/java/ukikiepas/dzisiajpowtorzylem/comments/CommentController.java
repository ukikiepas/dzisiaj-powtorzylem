package ukikiepas.dzisiajpowtorzylem.comments;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.comments.models.Comment;
import ukikiepas.dzisiajpowtorzylem.comments.models.CommentDTO;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/comments")
public class CommentController {

    private final CommentService service;

    @GetMapping("/{section}/{sectionParticularId}")
    public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable String section, @PathVariable Long sectionParticularId) {
         return ResponseEntity.ok(service.getAllCommentsWithImages(section, sectionParticularId));
    }

    @PostMapping
    public Comment addNewComment(@RequestBody Comment newComment, HttpServletRequest request) {
       return service.addNewComment(newComment, request);
    }

    @PatchMapping("/{commentId}")
    public void editComment(@PathVariable Long commentId, @RequestBody String body) {
        service.editComment(commentId, body);
    }


    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        service.deleteComment(commentId);
    }

}
