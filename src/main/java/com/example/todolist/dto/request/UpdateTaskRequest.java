package com.example.todolist.dto.request;

import com.example.todolist.model.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskRequest {
    private String description;
    private Status status;
}
