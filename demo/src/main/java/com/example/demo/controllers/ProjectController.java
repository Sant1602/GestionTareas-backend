package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.Project.ProjectDto;
import com.example.demo.Dto.Project.ProjectPostDto;
import com.example.demo.Dto.Project.ProjectPutDto;
import com.example.demo.Dto.Project.ProjectResponseDto;
import com.example.demo.Dto.Project.ProjectUserDto;
import com.example.demo.services.IProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<ProjectDto> getProject() {
        return ResponseEntity.ok(projectService.getProject());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<ProjectResponseDto>> getProjectFilter(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name) {
        return ResponseEntity.ok(projectService.filterProject(code, name));
    }

    @PostMapping
    public ResponseEntity<ProjectResponseDto> postProject(@RequestBody ProjectPostDto projectPostDto) {
        ProjectResponseDto project = projectService.createProject(projectPostDto);
        return ResponseEntity.ok(project);
    }

    @PostMapping("/userProject")
    public ResponseEntity<ProjectResponseDto> postUserProject(@RequestBody ProjectUserDto userProject) {
        projectService.setProjectUser(userProject.projectId(), userProject.userId());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDto> putProject(@RequestParam Long Id,
            @RequestBody ProjectPutDto projectPutDto) {
        ProjectResponseDto project = projectService.putProject(Id, projectPutDto);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProject(@RequestParam Long Id) {
        projectService.removeProject(Id);
        return ResponseEntity.noContent().build();
    }
}