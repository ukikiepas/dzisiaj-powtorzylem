package ukikiepas.dzisiajpowtorzylem.reading.audio;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ukikiepas.dzisiajpowtorzylem.reading.audio.models.AudioService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/readings")
public class AudioController {

    private final AudioService service;

    @GetMapping("/audio/{readingId}")
    public ResponseEntity<Resource> streamAudio(@PathVariable Long readingId) {
        return service.getAudio(readingId);
    }
}
