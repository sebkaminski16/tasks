package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    public Task getTask(Long id) throws TaskNotFoundException {
        return this.taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }

    public Task saveTask(Task task) {
        return this.taskRepository.save(task);
    }
}
