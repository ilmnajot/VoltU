package uz.ilmnajot.voltu.model.common;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private boolean success;
    private String message;
    private HttpStatus httpStatus;
    private Map<String, String> errors = new HashMap<>();
    private Map<String, Object> meta = new HashMap<>();
    private Object data;

    public ApiResponse(boolean success, String message, HttpStatus status, Object data) {
        this.success = success;
        this.message = message;
        this.httpStatus = status;
        this.data = data;
    }
}
