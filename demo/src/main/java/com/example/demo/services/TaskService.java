package com.example.demo.services;

import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ProjectRepository;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.Task.TaskPostDto;
import com.example.demo.Dto.Task.TaskPutDto;
import com.example.demo.Dto.Task.TaskResponseDto;
import com.example.demo.helpers.TaskStatus;
import com.example.demo.mapper.Mappers;
import com.example.demo.models.Board;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;

@Service
public class TaskService implements ITaskService {
    private BoardRepository boardRepository;
    private TaskRepository taskRepository;
    private UserRepository userRespository;
    private ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRespository,
            BoardRepository boardRepository,
            ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRespository = userRespository;
        this.boardRepository = boardRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return Mappers.toDto(tasks, TaskResponseDto::new);
    }

    @Override
    public List<TaskResponseDto> getBoardTasks(Long Id){
        List<Task> tasks = taskRepository.findByBoard(Id);
        return Mappers.toDto(tasks, TaskResponseDto::new);
    }

    @Override
    public TaskResponseDto createTasks(TaskPostDto taskPostDto) {
        User user = userRespository.findById(taskPostDto.userId())
                .orElseThrow(() -> new RuntimeException("El usuario no existe"));
        Board board = boardRepository.findById(taskPostDto.boardId())
                .orElseThrow(() -> new RuntimeException("No existe el tablero al que asignas la tarea"));
        if (!projectRepository.existsUserInProject(board.getProject().getId(), user.getId())) {
            throw new RuntimeException("Este usuario no se encuentra en el proyecto");
        }
        Task task = new Task(
                taskPostDto.title(),
                taskPostDto.description(),
                user,
                board);
        task.setStatus(taskPostDto.status());
        Task savedTask = taskRepository.save(task);
        return new TaskResponseDto(savedTask);
    }

    @Override
    public TaskResponseDto putTask(Long Id, TaskPutDto taskPutDto) {
        Task task = taskRepository.findById(Id).orElseThrow(() -> new RuntimeException("No se encontro la tarea"));
        taskPutDto.update(task);
        return new TaskResponseDto(taskRepository.save(task));
    }

    @Override
    public TaskResponseDto changeStatus(Long Id, TaskStatus status) {
        Task task = taskRepository.findById(Id).orElseThrow(() -> new RuntimeException("No se encontro la tarea"));
        task.setStatus(status);
        return new TaskResponseDto(taskRepository.save(task));
    }

    @Override
    public void removeTask(Long Id) {
        Task task = taskRepository.findById(Id).orElseThrow(() -> new RuntimeException("No se encontro la tarea"));
        taskRepository.delete(task);
    }
}
