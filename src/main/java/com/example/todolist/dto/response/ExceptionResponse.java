package com.example.todolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ExceptionResponse {
    private HttpStatus httpStatus;
    private String message;
    private String exceptionClassName;
}
