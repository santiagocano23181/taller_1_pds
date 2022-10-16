package co.com.poli.taller_3_santiago_cano.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FilaException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public FilaException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
