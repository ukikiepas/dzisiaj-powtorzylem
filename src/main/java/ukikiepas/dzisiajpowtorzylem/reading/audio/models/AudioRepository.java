package ukikiepas.dzisiajpowtorzylem.reading.audio.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AudioRepository extends JpaRepository<AudioFile, Long> {

    Optional<AudioFile> findByReadingId(Long readingId);

}


