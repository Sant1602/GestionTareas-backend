package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Dto.Boards.BoardPostDto;
import com.example.demo.Dto.Boards.BoardPutDto;
import com.example.demo.Dto.Boards.BoardResponseDto;
import com.example.demo.mapper.Mappers;
import com.example.demo.models.Board;
import com.example.demo.models.Project;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ProjectRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService implements IBoardService {
    private final BoardRepository boardRepository;
    private final ProjectRepository projectRepository;

    public BoardService(BoardRepository boardRepository, ProjectRepository projectRepository) {
        this.boardRepository = boardRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public List<BoardResponseDto> getBoard() {
        List<Board> boards = boardRepository.findAll();
        return Mappers.toDto(boards, BoardResponseDto::new);
    }

    @Override
    public List<BoardResponseDto> getBoardProject(Long Id) {
        List<Board> boards = boardRepository.findByProjectId(Id);
        return Mappers.toDto(boards, BoardResponseDto::new);
    }

    @Override
    public BoardResponseDto boardFilter(Long Id) {
        Board boards = boardRepository.findById(Id)
        .orElseThrow(() -> new RuntimeException("No se encontro el tablero"));
        return new BoardResponseDto(boards);
    }

    @Override
    @Transactional
    public BoardResponseDto createBoard(BoardPostDto boardPostDto) {
        Project project = projectRepository.findById(boardPostDto.ProjectId())
                .orElseThrow(() -> new RuntimeException("No se encontro el proyecto"));
        Board board = new Board(boardPostDto.code(),
                boardPostDto.name(),
                boardPostDto.description(),
                boardPostDto.isActive(),
                project);
        project.getBoards().add(board);
        return new BoardResponseDto(boardRepository.save(board));

    }

    @Override
    public BoardResponseDto putBoard(Long id, BoardPutDto boardPutDto) {
        Board board = boardRepository.findById(boardPutDto.Id())
                .orElseThrow(() -> new RuntimeException("No se encontro el tablero"));
        boardPutDto.update(board);
        return new BoardResponseDto(boardRepository.save(board));
    }

    @Override
    public void removeBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new Error("No se encontro el tablero que desea eliminar"));
        boardRepository.delete(board);
    }

}
