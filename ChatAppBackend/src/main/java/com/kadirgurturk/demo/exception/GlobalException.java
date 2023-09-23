package com.kadirgurturk.demo.exception;

import com.kadirgurturk.demo.buisness.dto.ApıResponse;

import com.kadirgurturk.demo.buisness.dto.ErrorResponse;
import com.kadirgurturk.demo.data.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApıResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception) {
        ApıResponse<?> serviceResponse = new ApıResponse<>();
        List<ErrorResponse> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    ErrorResponse errorResponse = new ErrorResponse(error.getField(), error.getDefaultMessage());
                    errors.add(errorResponse);
                });

        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(errors);
        return serviceResponse;
    }

    @ExceptionHandler(UserExcepiton.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApıResponse<?> handleMethodArgumentException(UserExcepiton exception) {
        ApıResponse<?> serviceResponse = new ApıResponse<>();
        List<ErrorResponse> errors = new ArrayList<>();

        ErrorResponse errorResponse = new ErrorResponse(exception.getLocalizedMessage(), exception.getMessage());
        errors.add(errorResponse);

        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(errors);
        return serviceResponse;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApıResponse<?> illeagalArgumentException(IllegalArgumentException exception) {
        ApıResponse<?> serviceResponse = new ApıResponse<>();
        List<ErrorResponse> errors = new ArrayList<>();

        ErrorResponse errorResponse = new ErrorResponse(exception.getLocalizedMessage(), exception.getMessage());
        errors.add(errorResponse);

        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(errors);
        return serviceResponse;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApıResponse<?> badCredentialsException(BadCredentialsException exception) {
        ApıResponse<?> serviceResponse = new ApıResponse<>();
        List<ErrorResponse> errors = new ArrayList<>();

        ErrorResponse errorResponse = new ErrorResponse(exception.getLocalizedMessage(), exception.getMessage());
        errors.add(errorResponse);

        serviceResponse.setStatus("FAILED");
        serviceResponse.setErrors(errors);
        return serviceResponse;
    }

}
