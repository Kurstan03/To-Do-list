package com.example.todolist.exceptions.handler;

import com.example.todolist.dto.response.ExceptionResponse;
import com.example.todolist.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundHandler(NotFoundException e) {
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                e.getClass().getSimpleName());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        return new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                Objects.requireNonNull(e.getFieldError()).getDefaultMessage(),
                e.getClass().getSimpleName());
    }
}
