package ukikiepas.dzisiajpowtorzylem.reading.models;

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
@Table(name="readings")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readingId;
    private String title;
    private String shortDescription;
    private String content;
    //Todo ogarnac czy to dobry pomysl ze tu String a nie ENUM :DP
    private String level;

}
