package ukikiepas.dzisiajpowtorzylem.reading.audio.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ukikiepas.dzisiajpowtorzylem.reading.models.Reading;

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
    private Long readingId;
    private String fileName;
    private Time duration;
    private String format;
    private String filePath;

}
