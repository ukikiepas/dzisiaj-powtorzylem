package ukikiepas.dzisiajpowtorzylem.irregular.models;

import java.util.ArrayList;
import java.util.List;

public enum EnglishLevel {
    A1, A2, B1, B2, C1, C2;

    public static List<String> getAllLevelsUpTo(EnglishLevel maxLevel) {
        List<String> levels = new ArrayList<>();
        for (EnglishLevel level : EnglishLevel.values()) {
            levels.add(level.name());
            if (level == maxLevel) {
                break;
            }
        }
        return levels;
    }
}