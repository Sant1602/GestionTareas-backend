package com.example.demo.Dto.Project;

import com.example.demo.models.Project;

public record ProjectResponseDto(Long id,
        String code,
        String name,
        String description,
        boolean isActive) {

    public ProjectResponseDto(Project project) {
        this(project.getId(),
                project.getCode(),
                project.getName(),
                project.getDescription(),
                project.getActive());
    }
}