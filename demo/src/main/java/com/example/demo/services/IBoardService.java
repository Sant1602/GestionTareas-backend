package com.example.demo.services;

import java.util.List;

import com.example.demo.Dto.Boards.BoardPostDto;
import com.example.demo.Dto.Boards.BoardPutDto;
import com.example.demo.Dto.Boards.BoardResponseDto;

public interface IBoardService {

   List<BoardResponseDto> getBoard();
   List<BoardResponseDto>getBoardProject(Long Id);
   BoardResponseDto boardFilter(Long Id);
   BoardResponseDto createBoard(BoardPostDto boardPostDto);
   BoardResponseDto putBoard(Long id, BoardPutDto boardPutDto);
   void removeBoard(Long id);

    
}
