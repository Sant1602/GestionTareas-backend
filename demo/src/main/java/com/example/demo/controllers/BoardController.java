package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.Boards.BoardPostDto;
import com.example.demo.Dto.Boards.BoardPutDto;
import com.example.demo.Dto.Boards.BoardResponseDto;
import com.example.demo.services.IBoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final IBoardService boardService;

    public BoardController(IBoardService boardService){
        this.boardService = boardService;
    }
    
    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> getBoard() {
        return ResponseEntity.ok(boardService.getBoard());
    }

    @GetMapping("/projectId/{id}")
    public ResponseEntity<List<BoardResponseDto>> getBoardProject(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardProject(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<BoardResponseDto>> getFilterBoard(@RequestParam Long id) {
        return ResponseEntity.ok(boardService.getBoard());
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> postBoard(@RequestBody BoardPostDto boardPostDto) {
        BoardResponseDto board = boardService.createBoard(boardPostDto);
        return ResponseEntity.ok(board);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> putBoard(@RequestParam Long Id,@RequestBody BoardPutDto boardPutDto) {
        BoardResponseDto board = boardService.putBoard(Id, boardPutDto);
        return ResponseEntity.ok(board);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBoard(@RequestParam Long Id) {
        boardService.removeBoard(Id);
        return ResponseEntity.noContent().build();
    }
    
}

