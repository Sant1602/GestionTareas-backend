package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ForeignKey;
// java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false, unique = false)
    private int edad;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = true)
    private String second_last_name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true, unique = true, length = 60)
    @Email
    @Size(max = 60)
    private String gmail;

    @Column(nullable = false, unique = false)
    @DateTimeFormat
    private LocalDate date;

    @Column(nullable = true)
    @DateTimeFormat
    private LocalDate updateDate;

    @Column(nullable = true)
    @DateTimeFormat
    private LocalDate updatePassword;

    @Column(nullable = true)
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "fk_user_role"))
    private Role role;

    @ManyToMany(mappedBy = "user")
    private List<Project> project = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<>();

    public User(String username, String name, String last_name, String second_last_name, String password, int edad,
            String gmail, LocalDate date,
            LocalDate updateDate, LocalDate updatePassword, boolean isActive, Role role) {
        this.edad = edad;
        this.username = username;
        this.name = name;
        this.last_name = last_name;
        this.second_last_name = second_last_name;
        this.password = password;
        this.gmail = gmail;
        this.date = date;
        this.updateDate = updateDate;
        this.updatePassword = updatePassword;
        this.isActive = isActive;
        this.role = role;
    }

    public User() {
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSecond_last_name() {
        return second_last_name;
    }

    public void setSecond_last_name(String second_last_name) {
        this.second_last_name = second_last_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    // Fk
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getUpdatePassword() {
        return updatePassword;
    }

    public void setUpdatePassword(LocalDate updatePass) {
        this.updatePassword = updatePass;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public List<Project> getProjects() {
        return project;
    }

    public void setProjects(List<Project> projects) {
        this.project = projects;
    }

    
}
