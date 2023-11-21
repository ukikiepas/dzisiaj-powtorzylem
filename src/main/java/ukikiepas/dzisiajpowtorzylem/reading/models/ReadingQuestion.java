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
@Table(name="readings_questions")
public class ReadingQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @ManyToOne
    @JoinColumn(name = "reading_id", referencedColumnName = "readingId")
    private Reading reading;
    private String questionText;
    private String answerMarker;
}
