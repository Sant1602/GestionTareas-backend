package com.example.demo.Dto.Task;

import com.example.demo.helpers.TaskStatus;
import com.example.demo.models.Task;

public record TaskPutDto (
    String tittle,
    String description,
    Long userId,
    Long boardId,
    TaskStatus status) {
        public void update(Task task){
            task.setTittle(this.tittle);
            task.setDescription(this.description);
            task.setStatus(this.status);
        }

    
}
