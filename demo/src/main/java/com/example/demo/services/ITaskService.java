package com.example.demo.services;

import java.util.List;

import com.example.demo.Dto.Task.TaskPostDto;
import com.example.demo.Dto.Task.TaskPutDto;
import com.example.demo.Dto.Task.TaskResponseDto;
import com.example.demo.helpers.TaskStatus;


public interface ITaskService {

    List<TaskResponseDto> getAllTasks();
    List<TaskResponseDto> getBoardTasks(Long Id);
    TaskResponseDto createTasks(TaskPostDto taskPostDto);
    TaskResponseDto putTask(Long Id, TaskPutDto taskPutDto);
    TaskResponseDto changeStatus(Long Id, TaskStatus status);
    void removeTask(Long Id);
    
}
