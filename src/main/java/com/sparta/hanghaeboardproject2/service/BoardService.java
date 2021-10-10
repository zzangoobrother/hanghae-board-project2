package com.sparta.hanghaeboardproject2.service;

import com.sparta.hanghaeboardproject2.dto.BoardDto;
import com.sparta.hanghaeboardproject2.model.Board;
import com.sparta.hanghaeboardproject2.repository.BoardRepository;
import com.sparta.hanghaeboardproject2.security.MemberDetailsImpl;
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
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
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

    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        board.tranModifiedAt();
        return board;
    }

    @Transactional
    public Long updateBoard(Long id, BoardDto boardDto) {
        Board foundBoard = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        foundBoard.update(boardDto);
        return foundBoard.getId();
    }

    public void deleteBoard(Long id, MemberDetailsImpl memberDetails) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id를 찾을 수 없습니다.")
        );

        if (!board.getWriter().equals(memberDetails.getUsername())) {
            throw new IllegalArgumentException("해당 게시글 작성자만 삭제할 수 있습니다.");
        }

        boardRepository.deleteById(id);
    }
}
