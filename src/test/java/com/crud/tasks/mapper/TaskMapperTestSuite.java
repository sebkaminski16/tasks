package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskMapperTestSuite {

    private final TaskMapper taskMapper = new TaskMapper();

    @Test
    void testMapToTaskDto() {
        //arrange
        Task task = new Task(1L, "Test title", "Test content");
        //act
        TaskDto result = taskMapper.mapToTaskDto(task);
        //assert
        assertEquals(1L, result.getId());
        assertEquals("Test title", result.getTitle());
        assertEquals("Test content", result.getContent());
    }

    @Test
    void testMapToTask() {
        //arrange
        TaskDto taskDto = new TaskDto(2L, "DTO title", "DTO content");
        //act
        Task result = taskMapper.mapToTask(taskDto);
        //assert
        assertEquals(2L, result.getId());
        assertEquals("DTO title", result.getTitle());
        assertEquals("DTO content", result.getContent());
    }

    @Test
    void testMapToTaskDtoList() {
        //arrange
        List<Task> taskList = List.of(
                new Task(1L, "Task 1", "Content 1"),
                new Task(2L, "Task 2", "Content 2")
        );
        //act
        List<TaskDto> result = taskMapper.mapToTaskDtoList(taskList);
        //asser
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Task 1", result.get(0).getTitle());
        assertEquals("Content 2", result.get(1).getContent());
    }
}
