package com.vitor.helpdesck.resources.exceptions;

import com.vitor.helpdesck.services.exceptions.DataIntegriyViolationException;
import com.vitor.helpdesck.services.exceptions.ObjectnotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectnotFoundException.class)
    public ResponseEntity<StandarError> objectnotFoundException(ObjectnotFoundException ex,
    HttpServletRequest request){
        StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
        "Object Not Found", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegriyViolationException.class)
    public ResponseEntity<StandarError> dataIntegriyViolationException(DataIntegriyViolationException ex,
                                                                HttpServletRequest request){
        StandarError error = new StandarError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Violação de dados", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandarError> validationErrors(MethodArgumentNotValidException ex,
                                                                       HttpServletRequest request){
        ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
                "Validation error", "Erro na validação dos campos", request.getRequestURI());

        for(FieldError x : ex.getBindingResult().getFieldErrors()){
            errors.addError(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}
