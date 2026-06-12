package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.Task.TaskPostDto;
import com.example.demo.Dto.Task.TaskPutDto;
import com.example.demo.Dto.Task.TaskResponseDto;
import com.example.demo.Dto.Task.TaskStatusUpdateDTO;
import com.example.demo.services.ITaskService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> getTasks() {
        List<TaskResponseDto> task = taskService.getAllTasks();
        return ResponseEntity.ok(task);
    }

    @GetMapping("/boardId/{id}")
    public ResponseEntity<List<TaskResponseDto>> getBoardTasks(@PathVariable Long id) {
        List<TaskResponseDto> task = taskService.getBoardTasks(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody TaskPostDto taskPost) {
        TaskResponseDto task = taskService.createTasks(taskPost);
        return ResponseEntity.ok(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> putTask(@PathVariable long id, @RequestBody TaskPutDto taskPutDto) {
        TaskResponseDto task = taskService.putTask(id, taskPutDto);
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateStatus(@PathVariable Long id,
            @RequestBody TaskStatusUpdateDTO taskPatchDto) {
        taskService.changeStatus(id, taskPatchDto.status());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable long id) {
        taskService.removeTask(id);
        return ResponseEntity.noContent().build();
    }
}
