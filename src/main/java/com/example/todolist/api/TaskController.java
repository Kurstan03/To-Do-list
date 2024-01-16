package com.example.todolist.api;

import com.example.todolist.dto.request.TaskRequest;
import com.example.todolist.dto.request.UpdateTaskRequest;
import com.example.todolist.dto.response.SimpleResponse;
import com.example.todolist.model.Task;
import com.example.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task", description = "Tasks management APIs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskController {
    private final TaskService taskService;

    @Operation(
            summary = "Get all Tasks",
            description = "This endpoint to get list of Task objects with id, description and status"
    )
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(
            summary = "Get Task by id",
            description = "This endpoint to get Task object with id, description and status by id"
    )
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable("id") Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @Operation(summary = "Add new Task", description = "This endpoint to add new Task")
    @PostMapping
    public SimpleResponse addNewTask(@RequestBody @Valid TaskRequest request) {
        return taskService.addNewTask(request);
    }

    @Operation(summary = "Update the Task", description = "This endpoint to update task by id")
    @PutMapping("/{id}")
    public SimpleResponse updateTask(
            @PathVariable("id") Long taskId,
            @RequestBody UpdateTaskRequest request) {
        return taskService.updateTask(taskId, request);
    }

    @Operation(summary = "Delete the Task", description = "This endpoint to delete Task by id")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteTask(@PathVariable("id") Long taskId) {
        return taskService.deleteTask(taskId);
    }
}
