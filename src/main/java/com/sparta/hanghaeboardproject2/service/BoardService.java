package com.sparta.hanghaeboardproject2.service;

import com.sparta.hanghaeboardproject2.dto.BoardDto;
import com.sparta.hanghaeboardproject2.model.Board;
import com.sparta.hanghaeboardproject2.repository.BoardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getBoards() {
        Board board = new Board("test", "test", "test");
        boardRepository.save(board);
        List<Board> boardList = boardRepository.findAll();
        for (Board board1 : boardList) {
            board1.tranModifiedAt();
        }
        return boardList;
    }

    public Board createBoard(BoardDto boardDto) {
        Board board = new Board(boardDto);
        boardRepository.save(board);
        return board;
    }
}
