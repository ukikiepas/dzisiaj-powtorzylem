package ukikiepas.dzisiajpowtorzylem.reading.audio.models;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AudioService {

    private final AudioRepository repository;

    public ResponseEntity<Resource> getAudio(Long readingId) {
        try {
            Path path = findPathName(readingId);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("audio/mpeg"))
                        .body(resource);
            }
        } catch (Exception e) {
            //Todo do somethign (LOOOOGEER)
        }
        return ResponseEntity.internalServerError().build();
    }




    private Path findPathName(Long readingId) throws FileNotFoundException {
        AudioFile audioFile = repository.findByReadingId(readingId)
                .orElseThrow(() -> new FileNotFoundException("Audio file not found for readingId: " + readingId));

        return Paths.get(audioFile.getFilePath(), audioFile.getFileName() + "."  + audioFile.getFormat());
    }
}
