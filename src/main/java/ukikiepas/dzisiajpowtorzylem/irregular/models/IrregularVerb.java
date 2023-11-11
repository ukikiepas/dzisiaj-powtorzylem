package ukikiepas.dzisiajpowtorzylem.irregular.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="irregular_verbs")
public class IrregularVerb {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "irregular_seq")
    @SequenceGenerator(name = "irregular_seq", sequenceName = "irregular_verbs_verb_id_seq", allocationSize = 1)
    private Long verbId;

    @NotNull
    private String baseForm;

    @NotNull
    private String pastSimple;

    @NotNull
    private String pastParticiple;

    @NotNull
    private String translation;

    @NotNull
    private String exampleSentence;

    @NotNull
    private String level;

}
