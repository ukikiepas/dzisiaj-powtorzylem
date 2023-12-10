package ukikiepas.dzisiajpowtorzylem.vocabulary.sheduler;

import org.springframework.stereotype.Component;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.VocabularyDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class DailyVocabularyCache {
    private static List<VocabularyDto> currentVocabularyDto = new ArrayList<>();

    public static void updateWords(List<VocabularyDto> wordsDto) {
        currentVocabularyDto.clear();
        currentVocabularyDto.addAll(wordsDto);
    }

    public static List<VocabularyDto> getCurrentVocabularyDto() {
        return new ArrayList<>(currentVocabularyDto);
    }
}
