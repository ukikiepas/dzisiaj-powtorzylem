package ukikiepas.dzisiajpowtorzylem.reading.mappers;


import org.mapstruct.Mapper;
import ukikiepas.dzisiajpowtorzylem.reading.models.QuestionWithAnswersDto;
import ukikiepas.dzisiajpowtorzylem.reading.models.ReadingAnswer;
import ukikiepas.dzisiajpowtorzylem.reading.models.ReadingQuestion;

@Mapper(componentModel = "spring")
public interface QuestionAnswerMapper {

    QuestionWithAnswersDto questionToQuestionDTO(ReadingQuestion question);


    QuestionWithAnswersDto.AnswerDTO answerToAnswerDTO(ReadingAnswer answer);

}

