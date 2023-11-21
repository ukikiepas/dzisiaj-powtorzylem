package ukikiepas.dzisiajpowtorzylem.reading.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="readings_answers")
public class ReadingAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "questionId")
    private ReadingQuestion readingQuestion;
    private String answerText;
    private boolean isCorrect;

}
