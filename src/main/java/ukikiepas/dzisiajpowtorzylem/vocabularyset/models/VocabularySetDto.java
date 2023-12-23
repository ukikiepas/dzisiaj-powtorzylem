package ukikiepas.dzisiajpowtorzylem.vocabularyset.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.VocabularyDto;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularySetDto {

    private Long id;
    private String title;
    private String description;
    private String creator;
    private String category;
    private Boolean isPublic;
    private Boolean isActive;
    private LocalDate lastReviewed;
    private Boolean isCreatedByAdmin;
    private LocalDate creationDate;
    private Set<VocabularyDto> vocabularyDtos;

}
