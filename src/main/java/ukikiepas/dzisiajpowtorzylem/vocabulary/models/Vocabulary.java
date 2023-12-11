package ukikiepas.dzisiajpowtorzylem.vocabulary.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ukikiepas.dzisiajpowtorzylem.user.models.User;
import ukikiepas.dzisiajpowtorzylem.vocabularyset.models.VocabularySet;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vocabulary")
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vocabulary_seq")
    @SequenceGenerator(name = "vocabulary_seq",
            sequenceName = "vocabulary_word_id_seq",
            allocationSize = 1)
    private Long wordId;
    private String word;
    private String translation;
    private String definition;
    private String level;
    private String imageLocation;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users;
    @ManyToMany(mappedBy = "vocabularies", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VocabularySet> vocabularySets = new HashSet<>();
    private Boolean isApproved;

}
