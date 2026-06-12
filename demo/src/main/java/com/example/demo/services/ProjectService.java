package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.Project.ProjectDto;
import com.example.demo.Dto.Project.ProjectPostDto;
import com.example.demo.Dto.Project.ProjectPutDto;
import com.example.demo.Dto.Project.ProjectResponseDto;
import com.example.demo.mapper.Mappers;
import com.example.demo.models.Project;
import com.example.demo.models.User;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService implements IProjectService {

    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    public ProjectService(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<ProjectResponseDto> filterProject(String code, String name) {
        List<Project> projects = projectRepository.findByFilter(code, name);
        return Mappers.toDto(projects, ProjectResponseDto::new);
    }

    @Override
    public ProjectDto getProject() {
        int amount;
        List<Project> projects = projectRepository.findAll();
        amount = projects.size();
        return new ProjectDto(Mappers.toDto(projects, ProjectResponseDto::new), amount);
    }

    @Override
    public ProjectResponseDto createProject(ProjectPostDto projectPostDto) {
        Project project = new Project(projectPostDto.code(),
                projectPostDto.name(),
                projectPostDto.description(),
                projectPostDto.isActive());
        return new ProjectResponseDto(projectRepository.save(project));
    }

    @Override
    public ProjectResponseDto putProject(Long id, ProjectPutDto projectPutDto) {
        Project project = projectRepository.findById(projectPutDto.Id())
                .orElseThrow(() -> new Error("No se encontro el proyecto"));
        projectPutDto.update(project);
        projectRepository.save(project);
        return new ProjectResponseDto(project);
    }

    @Override
    public void removeProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new Error("No se encontro el proyecto"));
        projectRepository.delete(project);
    }

    @Transactional
    public void setProjectUser(Long projectId, Long userId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Error("No se encontro el proyecto"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Error("No se encontro el usuario"));
        project.getUser().add(user);
        user.getProjects().add(project);
        projectRepository.save(project);
    }
}
