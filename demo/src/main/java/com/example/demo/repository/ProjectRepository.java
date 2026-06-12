package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("""
                SELECT p FROM Project p
                WHERE (:code IS NULL OR p.code = :code)
                AND (:name IS NULL OR p.name LIKE CONCAT('%', CAST(:name AS string), '%'))
            """)
    List<Project> findByFilter(@Param("code") String code, @Param("name") String name);

    @Query("""
            SELECT COUNT(u) > 0 FROM Project p
            JOIN p.user u
            WHERE p.id = :projectId AND u.id = :userId
            """)
    boolean existsUserInProject(Long projectId, Long userId);
}
