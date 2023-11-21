package ukikiepas.dzisiajpowtorzylem.reading.models;

import lombok.Data;

import java.util.List;

@Data
public class QuestionWithAnswersDto {
    private Long questionId;
    private String questionText;
    private List<AnswerDTO> answers;
    private String answerMarker;


    @Data
    public static class AnswerDTO {
        private Long answerId;
        private String answerText;
        private boolean isCorrect;
    }
}
