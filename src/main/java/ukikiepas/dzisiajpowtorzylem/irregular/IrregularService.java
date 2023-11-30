package ukikiepas.dzisiajpowtorzylem.irregular;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ukikiepas.dzisiajpowtorzylem.irregular.mappers.IrregularVerbMapper;
import ukikiepas.dzisiajpowtorzylem.irregular.models.EnglishLevel;
import ukikiepas.dzisiajpowtorzylem.irregular.models.IrregularVerb;
import ukikiepas.dzisiajpowtorzylem.irregular.models.IrregularVerbDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IrregularService {

    private final IrregularRepository irregularVerbRepository;
    private final IrregularVerbMapper irregularVerbMapper;


    public List<IrregularVerbDto> getRandomVerbs(int count, String level) {
        List<String> levels = determineLevels(level);
        List<IrregularVerb> verbs = irregularVerbRepository.findRandomVerbsByLevel(levels, count);
        return verbs.stream()
                .map(irregularVerbMapper::verbToDto)
                .collect(Collectors.toList());
    }

    private List<String> determineLevels(String levelString) {
        EnglishLevel maxLevel = EnglishLevel.valueOf(levelString.toUpperCase());
        return EnglishLevel.getAllLevelsUpTo(maxLevel);
    }

}
