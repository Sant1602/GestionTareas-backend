package com.example.demo.Dto.Task;

import com.example.demo.helpers.TaskStatus;
import com.example.demo.models.Task;

public record TaskResponseDto(Long id, String title, String description, TaskStatus status,
        Long userId, Long boardId) {
    public TaskResponseDto(Task task) {
        this(task.getId(), task.getTittle(), task.getDescription(), task.getStatus(),
                task.getUser().getId(), task.getBoard().getId());
    }
}
