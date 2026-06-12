package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("""
                SELECT b FROM Board b
                WHERE b.project.id = :projectId
            """)
    List<Board> findByProjectId(@Param("projectId") Long projectId);
}
