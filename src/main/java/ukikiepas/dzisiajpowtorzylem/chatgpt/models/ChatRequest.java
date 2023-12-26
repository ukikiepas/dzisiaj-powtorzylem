package ukikiepas.dzisiajpowtorzylem.chatgpt.models;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatRequest {

    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    public ChatRequest(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.n = 1;  // Ustawienie domyślnej wartości
    }
}
// getters and setters

