package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.dto.BoardDto;
import com.sparta.hanghaeboardproject2.model.Answer;
import com.sparta.hanghaeboardproject2.model.Board;
import com.sparta.hanghaeboardproject2.security.MemberDetailsImpl;
import com.sparta.hanghaeboardproject2.service.AnswerService;
import com.sparta.hanghaeboardproject2.service.BoardService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;
    private final AnswerService answerService;

    public BoardController(BoardService boardService, AnswerService answerService) {
        this.boardService = boardService;
        this.answerService = answerService;
    }

    // 게시판 등록 페이지 이동
    @GetMapping("/board/write")
    public String getBoardWriteForm(@AuthenticationPrincipal MemberDetailsImpl memberDetails, Model model) {
        if (memberDetails != null) {
            model.addAttribute("writer", memberDetails.getUsername());
        }

        return "boardAddForm";
    }

    // 게시판 등록
    @PostMapping("/board/write")
    public String createBoard(BoardDto boardDto) {
        boardService.createBoard(boardDto);
        return "redirect:/";
    }

    // 게시판 단건 조회
    @GetMapping("/board/only/{id}")
    public String getBoard(@PathVariable Long id, @AuthenticationPrincipal MemberDetailsImpl memberDetails, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);

        List<Answer> answerList = answerService.getBoard(id);
        model.addAttribute("answers", answerList);

        if (memberDetails != null) {
            model.addAttribute("username", memberDetails.getMember().getUsername());
        }

        return "board";
    }

    // 게시판 수정 페이지 이동
    @GetMapping("/board/update/{id}")
    public String getUpdateBoard(@PathVariable Long id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);
        return "boardUpdateForm";
    }

    // 게시판 수정
    @PostMapping("/board/update/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute BoardDto boardDto) {
        boardService.updateBoard(id, boardDto);
        return "redirect:/board/{id}";
    }
}
