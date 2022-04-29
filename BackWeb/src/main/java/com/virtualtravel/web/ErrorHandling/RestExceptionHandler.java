package com.virtualtravel.web.ErrorHandling;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorOutputDto errorOutputDto = new ErrorOutputDto(HttpStatus.NOT_FOUND.value());
        errorOutputDto.setMsgError(ex.getMessage());
        return buildResponseEntity(errorOutputDto);
    }

    @ExceptionHandler(NoSeatsAvailableException.class)
    public ResponseEntity<Object> handleNoSeatsAvailableException(NoSeatsAvailableException ex) {
        ErrorOutputDto errorOutputDto = new ErrorOutputDto(HttpStatus.NOT_ACCEPTABLE.value());
        errorOutputDto.setMsgError(ex.getMessage());
        return buildResponseEntity(errorOutputDto);
    }

    @ExceptionHandler(RequiredQueryParamException.class)
    public ResponseEntity<Object> handleRequiredQueryParamException(RequiredQueryParamException ex) {
        ErrorOutputDto errorOutputDto = new ErrorOutputDto(HttpStatus.NOT_ACCEPTABLE.value());
        errorOutputDto.setMsgError(ex.getMessage());
        return buildResponseEntity(errorOutputDto);
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<Object> handleTokenNotValidException(TokenNotValidException ex) {
        ErrorOutputDto errorOutputDto = new ErrorOutputDto(HttpStatus.NOT_ACCEPTABLE.value());
        errorOutputDto.setMsgError(ex.getMessage());
        return buildResponseEntity(errorOutputDto);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorOutputDto errorOutputDto) {
        return new ResponseEntity<>(errorOutputDto, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String field = ((FieldError) error).getField();
            String errorMsg = error.getDefaultMessage();
            errors.put(field, errorMsg);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
