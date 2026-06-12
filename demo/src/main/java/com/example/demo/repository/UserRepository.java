package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByGmail(String gmail);
    User findByUsername(String username);
    User findByGmail(String gmail);
}

