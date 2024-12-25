package itmo.is.lab1.exceptionHandler.customExceptions;

import java.util.*;
import java.util.stream.Collectors;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.ImportFormatException;
import itmo.is.lab1.exceptionHandler.MinIOConnectException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import org.postgresql.util.PSQLException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("error", "Invalid request format");
        responseBody.put("message", ex.getMostSpecificCause().getMessage()); // Более точное сообщение об ошибке

        return new ResponseEntity<>(responseBody, headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        responseBody.put("error", errors);

        return new ResponseEntity<>(responseBody, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ExpiredJwtException.class, MalformedJwtException.class, SignatureException.class})
    public ResponseEntity<Object> handleJwtExceptions(RuntimeException ex) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", new Date());
        response.put("error", ex instanceof ExpiredJwtException ? "Token is expired" : "Incorrect token");
        response.put("message", ex.getMessage());

        HttpStatus status = ex instanceof ExpiredJwtException ? HttpStatus.UNAUTHORIZED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(response, status);
    }


    @ExceptionHandler({NotEnoughAccessLevelToData.class})
    public ResponseEntity<Object> handleNotEnoughAccessLevelToData(
            NotEnoughAccessLevelToData ex, WebRequest request) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.UNAUTHORIZED.value());
        responseBody.put("error", "Attempt to access someone else's data");
        responseBody.put("message", ex.getMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({DbException.class})
    public ResponseEntity<Object> handleDbException(
            DbException ex, WebRequest request) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", HttpStatus.NOT_FOUND.value());
        responseBody.put("error", ex.getMessage());

        return new ResponseEntity<>(responseBody, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Throwable rootCause = getRootCause(ex);
        if (rootCause instanceof PSQLException) {
            String message = rootCause.getMessage();
            if (message.contains("violates foreign key constraint")) {
                return ResponseEntity.badRequest().body("Невозможно удалить запись: она используется в других объектах.");
            }
        }
        return ResponseEntity.internalServerError().body("Ошибка базы данных.");
    }
    @ExceptionHandler(ImportFormatException.class)
    public ResponseEntity<String> handleImportFormatException(ImportFormatException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    private Throwable getRootCause(Throwable throwable) {
        Throwable rootCause = throwable;
        while (rootCause.getCause() != null && rootCause != rootCause.getCause()) {
            rootCause = rootCause.getCause();
        }
        return rootCause;
    }

    @ExceptionHandler(MinIOConnectException.class)
    public ResponseEntity<String> handleMinIOConnectionException(MinIOConnectException ex) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}