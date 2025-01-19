package uz.ilmnajot.voltu.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private Boolean success;
    private String message;
    private HttpStatus httpStatus;
    private Map<String, String> errors = new HashMap<>();
    private Map<String, Object> meta = new HashMap<>();
    private Object data;

    public ApiResponse(Boolean success, String message, HttpStatus status, Object data) {
        this.success = success;
        this.message = message;
        this.httpStatus = status;
        this.data = data;
    }
}
