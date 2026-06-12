package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.*;


@Entity
public class PasswordReset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime dateExpire;

     @OneToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_sesion_user"))
    private User user;

    public PasswordReset() {
    }

    public PasswordReset(String token, LocalDateTime dateExpire, User user) {
        this.token = token;
        this.dateExpire = dateExpire;
        this.user = user;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(LocalDateTime dateExpire) {
        this.dateExpire = dateExpire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

}
