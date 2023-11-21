package ukikiepas.dzisiajpowtorzylem.reading.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "readings_words")
public class ReadingWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wordId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "reading_id", referencedColumnName = "readingId")
    private Reading reading;
    private String word;
    private String translation;
    private String definition;

}

