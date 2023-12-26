package ukikiepas.dzisiajpowtorzylem.chatgpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ukikiepas.dzisiajpowtorzylem.chatgpt.models.ChatRequest;
import ukikiepas.dzisiajpowtorzylem.chatgpt.models.ChatResponse;

import java.util.Collections;

@RestController
@RequestMapping("/api/v1/auth")
public class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(@RequestBody String prompt) {
        // create a request
        ChatRequest request = new ChatRequest(model, prompt);

        // call the API
        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return ResponseEntity.ok(new ChatResponse()); // Zwraca pustą listę, jeśli nie ma odpowiedzi
        }

        return ResponseEntity.ok(response); // Zwraca odpowiedź, która zawiera listę wyborów
    }
}

