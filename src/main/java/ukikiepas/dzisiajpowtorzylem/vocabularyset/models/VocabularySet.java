package ukikiepas.dzisiajpowtorzylem.vocabularyset.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ukikiepas.dzisiajpowtorzylem.vocabulary.models.Vocabulary;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VocabularySet")
public class VocabularySet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vocabulary_set_seq")
    @SequenceGenerator(name = "vocabulary_set_seq", sequenceName = "vocabulary_set_id_seq", allocationSize = 1)
    private Long setId;
    private String title;
    private String description;
    private String creator;
    private String category;
    private Boolean isPublic;
    private Boolean isActive;
    private LocalDate lastReviewed;
    private Boolean isCreatedByAdmin;
    private LocalDate creationDate;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "vocabulary_vocabulary_set",
            joinColumns = @JoinColumn(name = "set_id"),
            inverseJoinColumns = @JoinColumn(name = "word_id")
    )
    private Set<Vocabulary> vocabularies = new HashSet<>();

}
