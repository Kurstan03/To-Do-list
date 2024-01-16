package com.example.todolist.service.impl;

import com.example.todolist.dto.request.TaskRequest;
import com.example.todolist.dto.request.UpdateTaskRequest;
import com.example.todolist.dto.response.SimpleResponse;
import com.example.todolist.exceptions.NotFoundException;
import com.example.todolist.model.Status;
import com.example.todolist.model.Task;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new NotFoundException("Task with id %s not found".formatted(taskId)));
    }

    @Override
    public SimpleResponse addNewTask(TaskRequest request) {
        Task task = new Task();
        task.setDescription(request.getDescription());
        task.setStatus(Status.NOT_DONE);
        taskRepository.save(task);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Task saved successfully!")
                .build();
    }

    @Override
    public SimpleResponse updateTask(Long taskId, UpdateTaskRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new NotFoundException("Task with id %s not found".formatted(taskId)));
        task.setDescription(request.getDescription().isBlank() ? task.getDescription() : request.getDescription());
        task.setStatus(request.getStatus() == null ? task.getStatus() : request.getStatus());
        taskRepository.save(task);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Task updated successfully!")
                .build();
    }

    @Override
    public SimpleResponse deleteTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(
                        () -> new NotFoundException("Task with id %s not found".formatted(taskId)));
        taskRepository.delete(task);

        return SimpleResponse
                .builder()
                .httpStatus(HttpStatus.OK)
                .message("Task deleted successfully!")
                .build();
    }
}
