package uz.ilmnajot.voltu.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandlerController {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    public HttpEntity<?> handleException(ResourceNotFoundException exception, WebRequest request) {
        logger.error("Resource Not found {} ", exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAlreadyExistsException(AlreadyExistsException exception, WebRequest request) {
        logger.error("AlreadyExistsException: {}", exception.getMessage(), exception);

        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.CONFLICT.value(), // HTTP 409 for "Conflict"
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException exception, WebRequest request) {
        logger.error("ValidationException: {}", exception.getMessage(), exception);

        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.value(), // HTTP 400 for "Bad Request"
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception exception, WebRequest request) {
        logger.error("Unhandled exception: {}", exception.getMessage(), exception);

        ErrorResponse errorResponse = new ErrorResponse(
                "An unexpected error occurred. Please try again later.", // User-friendly message
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // HTTP 500 for "Internal Server Error"
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        logger.error("AccessDeniedException: {}", exception.getMessage(), exception);

        ErrorResponse errorResponse = new ErrorResponse(
                "Access denied. You do not have permission to perform this action.",
                HttpStatus.FORBIDDEN.value(), // HTTP 403 for "Forbidden"
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException exception, WebRequest request) {
        logger.error("AuthenticationException: {}", exception.getMessage(), exception);

        ErrorResponse errorResponse = new ErrorResponse(
                "Authentication failed. Please check your credentials and try again.",
                HttpStatus.UNAUTHORIZED.value(), // HTTP 401 for "Unauthorized"
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
