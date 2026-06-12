package com.example.demo.models;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;

@Entity
@Table(name = "Sesion")
public class Sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tokenRefresh;

    @Column(nullable = false)
    private LocalDateTime dateExpire;

    @OneToOne
    @JoinColumn(name = "UserID", foreignKey = @ForeignKey(name = "fk_sesion_user"))
    private User user;

    public Sesion() {
    }

    public Sesion(String tokenRefresh, LocalDateTime dateExpire, User user) {
        this.tokenRefresh = tokenRefresh;
        this.dateExpire = dateExpire;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTokenRefresh() {
        return tokenRefresh;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.tokenRefresh = tokenRefresh;
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
