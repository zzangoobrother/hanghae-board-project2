package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.model.Board;
import com.sparta.hanghaeboardproject2.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final BoardService boardService;

    public HomeController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Board> boardList = boardService.getBoards();
        model.addAttribute("boards", boardList);
        return "index";
    }
}
