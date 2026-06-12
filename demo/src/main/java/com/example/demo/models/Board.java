package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Tablero")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, length = 7, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column
    private boolean isActive;

    @OneToMany(mappedBy = "board")
    private List<Task> tasks = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "ProjectID", foreignKey = @ForeignKey(name = "fk_board_project"))
    private Project project;

    public Board(String code, String name, String description, boolean isActive, Project project) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.project = project;
    }

    public Board() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Long getId() {
        return Id;
    }

    

}
