package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;


import jakarta.persistence.*;


@Entity
@Table(name = "Proyecto")
public class Project {
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

    @OneToMany(mappedBy = "project")
    private List<Board> boards = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "project_user",
        joinColumns = @JoinColumn(name = "projectID"),
        inverseJoinColumns = @JoinColumn(name = "userID")
    )
    private List<User> user = new ArrayList<>();

    public Project(String code, String name, String description, boolean isActive) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.isActive = isActive;
    }

    public Project(){}

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

    public boolean getActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Long getId() {
        return Id;
    }

    public boolean isActive() {
        return isActive;
    }

    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    
}
