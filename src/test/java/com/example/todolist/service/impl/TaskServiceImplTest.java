package com.example.todolist.service.impl;

import com.example.todolist.dto.request.TaskRequest;
import com.example.todolist.dto.request.UpdateTaskRequest;
import com.example.todolist.dto.response.SimpleResponse;
import com.example.todolist.exceptions.NotFoundException;
import com.example.todolist.model.Status;
import com.example.todolist.model.Task;
import com.example.todolist.repository.TaskRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {
    @Mock
    private TaskRepository repository;

    private TaskServiceImpl service;

    private static Task testTask;

    @BeforeAll
    static void setUp() {
        testTask = Task
                .builder()
                .id(123L)
                .description("Read the book Harry Potter")
                .status(Status.NOT_DONE)
                .build();
    }

    @BeforeEach
    void init() {
        service = new TaskServiceImpl(repository);
    }

    @Test
    void testGetAllTasks() {
        List<Task> mockTasks = new ArrayList<>();
        mockTasks.add(testTask);
        Mockito.when(repository.findAll()).thenReturn(mockTasks);

        List<Task> result = service.getAllTasks();

        assertEquals(mockTasks, result);
    }

    @Test
    void testGetTaskById() {
        Mockito.when(repository.findById(testTask.getId())).thenReturn(Optional.of(testTask));

        Task result = service.getTaskById(testTask.getId());

        assertNotNull(result);
        assertEquals(testTask, result);
    }

    @Test
    void testGetTaskByIdNotFound() {
        Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getTaskById(1L));
    }

    @Test
    void testAddNewTask() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setDescription("Read the book");

        service.addNewTask(taskRequest);

        //Verify that the save method was called once with the correct arguments
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Task.class));

        //Catch the Task object that will be saved
        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
        Mockito.verify(repository).save(taskArgumentCaptor.capture());
        Task capturedTask = taskArgumentCaptor.getValue();

        assertNotNull(capturedTask);
        assertEquals(taskRequest.getDescription(), capturedTask.getDescription());
        assertEquals(capturedTask.getStatus(), Status.NOT_DONE);
    }

    @Test
    void testUpdateTask() {
        UpdateTaskRequest request = new UpdateTaskRequest();
        request.setDescription("Test task");
        request.setStatus(Status.DONE);

        Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(testTask));

        service.updateTask(1L, request);

        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Task.class));

        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
        Mockito.verify(repository).save(taskArgumentCaptor.capture());
        Task capturedTask = taskArgumentCaptor.getValue();

        assertNotNull(capturedTask);
        assertEquals(testTask.getId(), capturedTask.getId());
        assertEquals(request.getDescription(), capturedTask.getDescription());
        assertEquals(request.getStatus(), capturedTask.getStatus());
    }

    @Test
    void testUpdateNonExistentTask() {
        Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updateTask(1L, new UpdateTaskRequest()));
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void testDeleteTask() {
        Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(testTask));

        SimpleResponse simpleResponse = service.deleteTask(1L);

        Mockito.verify(repository, Mockito.times(1)).delete(Mockito.any(Task.class));
        assertNotNull(simpleResponse);
    }

    @Test
    void testDeleteNonExistentTask() {
        Mockito.when(repository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deleteTask(1L));
        Mockito.verify(repository, Mockito.never()).delete(Mockito.any());
    }
}