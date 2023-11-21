package ukikiepas.dzisiajpowtorzylem.reading;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.reading.models.QuestionWithAnswersDto;
import ukikiepas.dzisiajpowtorzylem.reading.models.Reading;
import ukikiepas.dzisiajpowtorzylem.reading.models.ReadingWord;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/readings")
@RequiredArgsConstructor


public class ReadingController {

    private final ReadingService readingService;

    @GetMapping("/level/{level}")
    public List<Reading> getAllReadingsByLevel(@PathVariable("level") String level){
        return readingService.getAllReadingsByLevel(level);
    }

    @GetMapping("/{id}")
    public Reading getReadingById (@PathVariable("id") Long id){
        return readingService.getReadingById(id);
    }

    @GetMapping("/questions/{id}")
    public List<QuestionWithAnswersDto> getQuestionsWithAnswers(@PathVariable("id") Long id){
        return readingService.getQuestionsWithAnswers(id);
    }

    @GetMapping("/words/{id}")
    public List<ReadingWord> getWordsForText(@PathVariable("id") Long id){
        return readingService.getWordsForText(id);
    }
}
