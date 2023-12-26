package ukikiepas.dzisiajpowtorzylem.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private ResponseStatus status;
    private String message;
    private T data;

    // Konstruktor bez danych
    public ApiResponse(ResponseStatus status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }
}
