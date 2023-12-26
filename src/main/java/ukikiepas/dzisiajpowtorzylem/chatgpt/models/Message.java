package ukikiepas.dzisiajpowtorzylem.chatgpt.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Message {

    private String role;
    private String content;

    // constructor, getters and setters
}
