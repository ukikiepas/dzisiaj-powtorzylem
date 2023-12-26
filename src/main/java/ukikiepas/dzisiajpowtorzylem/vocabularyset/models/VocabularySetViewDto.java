package ukikiepas.dzisiajpowtorzylem.vocabularyset.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularySetViewDto {

    private Long id;
    private String title;
    private String description;
    private String creator;
    private LocalDate creationDate;

}
