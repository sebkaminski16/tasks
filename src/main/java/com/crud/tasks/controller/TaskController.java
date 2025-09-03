package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private DbService dbService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getTasks() {
       List<Task> tasks = this.dbService.getAllTasks();
       return ResponseEntity.ok(this.taskMapper.mapToTaskDtoList(tasks));
    }

    @GetMapping(value = "{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable  Long taskId) throws TaskNotFoundException {
        return ResponseEntity.ok(this.taskMapper.mapToTaskDto(this.dbService.getTask(taskId)));
    }

    @DeleteMapping(value = "{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task task = this.taskMapper.mapToTask(taskDto);
        Task savedTask = this.dbService.saveTask(task);
        return ResponseEntity.ok(this.taskMapper.mapToTaskDto(savedTask));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTask(@RequestBody TaskDto taskDto) {
        Task task = this.taskMapper.mapToTask(taskDto);
        this.dbService.saveTask(task);
        return ResponseEntity.ok().build();
    }
}
