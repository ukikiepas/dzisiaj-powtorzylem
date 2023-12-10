package ukikiepas.dzisiajpowtorzylem.vocabulary.sheduler;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ukikiepas.dzisiajpowtorzylem.vocabulary.VocabularyService;
import ukikiepas.dzisiajpowtorzylem.vocabulary.mappers.VocabularyMapper;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;
import ukikiepas.dzisiajpowtorzylem.vocabulary.sheduler.models.DailyVocabulary;

import java.time.LocalDate;
import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class VocabularyScheduler {

    private final DailyVocabularyRepository dailyWordsRepository;

    private final VocabularyService vocabularyService;
    private final VocabularyMapper vocabularyMapper;


    @PostConstruct
    public void onApplicationEvent() {
        updateDailyWords();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduledUpdateDailyWords() {
        updateDailyWords();
    }

    public void updateDailyWords() {
        List<Vocabulary> words = vocabularyService.selectWordsForToday();
        for (Vocabulary word: words){
            DailyVocabulary dailyVocabulary = new DailyVocabulary(null,LocalDate.now(), word.getWordId());
            dailyWordsRepository.save(dailyVocabulary);
        }
        DailyVocabularyCache.updateWords(vocabularyMapper.vocabulariesToVocabularyDtos(words));
    }
}
