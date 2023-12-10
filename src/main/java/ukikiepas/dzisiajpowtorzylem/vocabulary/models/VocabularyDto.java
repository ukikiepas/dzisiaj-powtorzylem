package ukikiepas.dzisiajpowtorzylem.vocabulary.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyDto {

    private Long wordId;
    private String word;
    private String translation;
    private String definition;
    private String level;
    private String imageLocation;
    private Boolean isFavourited;
}
