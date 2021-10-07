package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.dto.BoardDto;
import com.sparta.hanghaeboardproject2.security.MemberDetailsImpl;
import com.sparta.hanghaeboardproject2.service.BoardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/board/write")
    public String getBoardWriteForm(@AuthenticationPrincipal MemberDetailsImpl memberDetails, Model model) {
        model.addAttribute("writer", memberDetails.getUsername());
        return "boardAddForm";
    }

    @PostMapping("/board/write")
    public String createBoard(BoardDto boardDto) {
        System.out.println(boardDto.getContents());
        System.out.println(boardDto.getTitle());
        System.out.println(boardDto.getWriter());
        boardService.createBoard(boardDto);
        return "redirect:/";
    }
}
