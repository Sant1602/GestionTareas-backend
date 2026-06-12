package com.example.demo.services;

import java.util.List;

import com.example.demo.Dto.Project.ProjectDto;
import com.example.demo.Dto.Project.ProjectPostDto;
import com.example.demo.Dto.Project.ProjectPutDto;
import com.example.demo.Dto.Project.ProjectResponseDto;

public interface IProjectService {

    ProjectDto getProject();
    List<ProjectResponseDto> filterProject(String code, String name);
    ProjectResponseDto createProject(ProjectPostDto projectPostDto);
    ProjectResponseDto putProject(Long id, ProjectPutDto projectPutDto);
    void setProjectUser(Long projectId, Long userId);
    void removeProject(Long id);

}
