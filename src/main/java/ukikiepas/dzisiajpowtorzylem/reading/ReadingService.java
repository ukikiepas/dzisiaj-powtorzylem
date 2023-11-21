package ukikiepas.dzisiajpowtorzylem.reading;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.reading.mappers.QuestionAnswerMapper;
import ukikiepas.dzisiajpowtorzylem.reading.models.*;
import ukikiepas.dzisiajpowtorzylem.reading.repositories.ReadingAnswersRepository;
import ukikiepas.dzisiajpowtorzylem.reading.repositories.ReadingQuestionsRepository;
import ukikiepas.dzisiajpowtorzylem.reading.repositories.ReadingRepository;
import ukikiepas.dzisiajpowtorzylem.reading.repositories.ReadingWordsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadingService {

    private final ReadingRepository readingRepository;
    private final ReadingQuestionsRepository readingQuestionsRepository;
    private final ReadingAnswersRepository readingAnswersRepository;
    private final ReadingWordsRepository readingWordsRepository;
    private final QuestionAnswerMapper questionAnswerMapper;

    public List<Reading> getAllReadingsByLevel(String level){
        return readingRepository.findAllByLevel(level);
    }

    public Reading getReadingById(Long id){
        return readingRepository.findByReadingId(id);
    }

    public List<QuestionWithAnswersDto> getQuestionsWithAnswers(Long readingId) {
        List<ReadingQuestion> questions = readingQuestionsRepository.findByReadingReadingId(readingId);
        return questions.stream().map(question -> {
            QuestionWithAnswersDto dto = questionAnswerMapper.questionToQuestionDTO(question);
            List<ReadingAnswer> answers = readingAnswersRepository.findByReadingQuestionQuestionId(question.getQuestionId());
            List<QuestionWithAnswersDto.AnswerDTO> answerDTOs = answers.stream()
                    .map(questionAnswerMapper::answerToAnswerDTO)
                    .collect(Collectors.toList());
            dto.setAnswers(answerDTOs);
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ReadingWord> getWordsForText(Long id) {
        return readingWordsRepository.findByReadingReadingId(id);
    }
}
