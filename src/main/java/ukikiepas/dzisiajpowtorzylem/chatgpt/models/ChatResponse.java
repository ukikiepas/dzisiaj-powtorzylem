package ukikiepas.dzisiajpowtorzylem.chatgpt.models;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ChatResponse {

    private List<Choice> choices;

    // constructors, getters and setters

    @Data
    @RequiredArgsConstructor
    public static class Choice {

        private int index;
        private Message message;

        // constructors, getters and setters
    }
}
