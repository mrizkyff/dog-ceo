package com.mrizkyff.dogceorestful.configuration;

import com.mrizkyff.dogceorestful.exception.BadRequestException;
import com.mrizkyff.dogceorestful.exception.BaseErrorDetail;
import com.mrizkyff.dogceorestful.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler ( MethodArgumentNotValidException.class )
    public ResponseEntity<BaseErrorDetail> handleValidationErrors(MethodArgumentNotValidException ex , WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).toList();
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now() , HttpStatus.BAD_REQUEST.value() , HttpStatus.BAD_REQUEST.getReasonPhrase() ,
                errors.toString() , request.getDescription(false));
        return new ResponseEntity<>(errorDetail , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler ( BadRequestException.class )
    public final ResponseEntity<BaseErrorDetail> handleBadRequestException(BadRequestException ex , WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now() , HttpStatus.BAD_REQUEST.value() , HttpStatus.BAD_REQUEST.getReasonPhrase() ,
                ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(errorDetail , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler ( HttpMessageNotReadableException.class )
    public final ResponseEntity<BaseErrorDetail> handleMessageNotReadableException(HttpMessageNotReadableException ex , WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now() , HttpStatus.BAD_REQUEST.value() , HttpStatus.BAD_REQUEST.getReasonPhrase() ,
                ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(errorDetail , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler ( DataNotFoundException.class )
    public final ResponseEntity<BaseErrorDetail> handleDataNotFoundException(DataNotFoundException ex , WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now() , HttpStatus.NOT_FOUND.value() , HttpStatus.NOT_FOUND.getReasonPhrase() ,
                ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(errorDetail , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler ( HttpClientErrorException.NotFound.class )
    public final ResponseEntity<BaseErrorDetail> handleHttpClientErrorExceptionNotFound(HttpClientErrorException.NotFound ex , WebRequest request) {
        BaseErrorDetail errorDetail = new BaseErrorDetail(LocalDateTime.now() , HttpStatus.NOT_FOUND.value() , HttpStatus.NOT_FOUND.getReasonPhrase() ,
                ex.getMessage() , request.getDescription(false));
        return new ResponseEntity<>(errorDetail , HttpStatus.NOT_FOUND);
    }

}

