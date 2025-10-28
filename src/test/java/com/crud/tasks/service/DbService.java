package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService; // testowana klasa

    @Mock
    private TaskRepository taskRepository; // mock repozytorium

    @Test
    void shouldGetAllTasks() {
        //arrange
        List<Task> tasks = List.of(new Task(1L, "title", "content"));
        when(taskRepository.findAll()).thenReturn(tasks);
        //act
        List<Task> result = dbService.getAllTasks();
        //assert
        assertEquals(1, result.size());
        verify(taskRepository, times(1)).findAll();
    }

}
