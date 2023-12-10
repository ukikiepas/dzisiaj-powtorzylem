package ukikiepas.dzisiajpowtorzylem.exception.types;

public class WordNotFoundException extends RuntimeException {
    public WordNotFoundException(Long wordId) {
        super("Word not found with id: " + wordId);
    }
}

