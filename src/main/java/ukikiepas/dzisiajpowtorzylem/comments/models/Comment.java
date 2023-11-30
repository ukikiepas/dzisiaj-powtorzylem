package ukikiepas.dzisiajpowtorzylem.comments.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @SequenceGenerator(name = "comment_seq", sequenceName = "comments_comment_id_seq", allocationSize = 1)
    private Long commentId;
    private String body;
    private String username;
    private Long userId;
    private Long parentId;
    private LocalDateTime createdAt;
}
