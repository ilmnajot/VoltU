package uz.ilmnajot.voltu.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private int code;
    private LocalDateTime timestamp;
    private Object details;

    public ErrorResponse(String message, int code, Object details) {
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now();
        this.details = details;
    }
}
