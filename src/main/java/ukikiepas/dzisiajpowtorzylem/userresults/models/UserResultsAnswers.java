package ukikiepas.dzisiajpowtorzylem.userresults.models;


import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "UserResultsAnswers")
public class UserResultsAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long resultId;
    private String vocabularyWord;
    private String userAnswer;
    @JsonProperty
    private boolean isCorrect;

}
