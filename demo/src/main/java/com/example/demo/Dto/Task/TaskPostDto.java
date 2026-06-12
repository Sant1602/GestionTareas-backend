package com.example.demo.Dto.Task;

import com.example.demo.helpers.TaskStatus;

public record TaskPostDto (String title, String description, Long userId, TaskStatus status,
    Long boardId
) {
    
}
