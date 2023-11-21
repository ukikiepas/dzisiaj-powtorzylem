package ukikiepas.dzisiajpowtorzylem.reading.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="audio_files")
public class AudioFile {

    //TODO jak juz bedzie hosting ot ogarnąć odtwarzanie dźwięku z plików na serwerze !
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long audioId;
    @ManyToOne
    @JoinColumn(name = "reading_id", referencedColumnName = "readingId")
    private Reading reading;
    private String filePath;
    private Time duration;
    private String format;

}
