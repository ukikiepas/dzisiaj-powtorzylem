package ukikiepas.dzisiajpowtorzylem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(TokenNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // można ustawić inny status odpowiedni do sytuacji
    public ResponseEntity<String> handleTokenNotFound(TokenNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    // Tutaj możesz dodać obsługę innych wyjątków
}
