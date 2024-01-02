package ukikiepas.dzisiajpowtorzylem.userresults.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResultDto {

    private Long resultId;
    private Long setId;
    private String username;
    private int correctAnswers;
    private int badAnswers;
    private double score;
    private Long durationTime;
    private LocalDateTime insertTime; // czas zrobienia zaproponuj inna nazwe
    @JsonProperty
    private List<UserResultsAnswers> answers;
}
