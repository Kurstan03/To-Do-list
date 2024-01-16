package com.example.todolist.service;

import com.example.todolist.dto.request.TaskRequest;
import com.example.todolist.dto.request.UpdateTaskRequest;
import com.example.todolist.dto.response.SimpleResponse;
import com.example.todolist.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<Task> getAllTasks();

    Task getTaskById(Long taskId);

    SimpleResponse addNewTask(TaskRequest request);

    SimpleResponse updateTask(Long taskId, UpdateTaskRequest request);

    SimpleResponse deleteTask(Long taskId);
}
