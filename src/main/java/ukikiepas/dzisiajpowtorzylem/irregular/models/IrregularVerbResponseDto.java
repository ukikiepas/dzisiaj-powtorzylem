package ukikiepas.dzisiajpowtorzylem.irregular.models;

import lombok.Data;

@Data
public class IrregularVerbResponseDto {

    private String baseForm;
    private String userPastSimple;
    private String userPastParticiple;
    private String userTranslation;
    private boolean isPastSimpleCorrect;
    private boolean isPastParticipleCorrect;
    private boolean isTranslationCorrect;


}
