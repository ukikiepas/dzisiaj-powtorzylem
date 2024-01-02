package ukikiepas.dzisiajpowtorzylem.userresults.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResultDto;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResults;
import ukikiepas.dzisiajpowtorzylem.userresults.models.UserResultsAnswers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResultsMapper {


    @Mapping(target = "id", source = "setId")
    @Mapping(target = "resultId", ignore = true)
    UserResults mapDtoToUserResults(UserResultDto userResultDto);

    default List<UserResultsAnswers> mapDtoToUserResultsAnswers(UserResultDto userResultDto, Long resultId) {
        return userResultDto.getAnswers().stream().map(answerDto -> {
            UserResultsAnswers answer = new UserResultsAnswers();
            answer.setResultId(resultId);
            answer.setVocabularyWord(answerDto.getVocabularyWord());
            answer.setUserAnswer(answerDto.getUserAnswer());
            answer.setCorrect(answerDto.isCorrect());
            return answer;
        }).toList();
    }
}
