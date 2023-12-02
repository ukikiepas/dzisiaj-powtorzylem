package ukikiepas.dzisiajpowtorzylem.comments.models;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private Long commentId;
    private String body;
    private String username;
    private Long parentId;
    private LocalDateTime createdAt;
    private String image;

    public CommentDTO(Comment comment, String image) {
        this.commentId = comment.getCommentId();
        this.body = comment.getBody();
        this.username = comment.getUsername();
        this.parentId = comment.getParentId();
        this.createdAt = comment.getCreatedAt();
        this.image = image;
    }
}