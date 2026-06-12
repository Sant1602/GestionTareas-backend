package com.example.demo.Dto.Project;

import com.example.demo.models.Project;

public record ProjectPutDto(Long Id,
        String code,
        String name,
        String description,
        boolean isActive) {
    public void update(Project project) {
        project.setName(this.name);
        project.setCode(this.code);
        project.setDescription(this.description);
        project.setActive(this.isActive);
    }
}
