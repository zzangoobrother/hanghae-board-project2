package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.dto.AnswerDto;
import com.sparta.hanghaeboardproject2.security.MemberDetailsImpl;
import com.sparta.hanghaeboardproject2.service.AnswerService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/api/answer")
    public Long createAnswer(@RequestBody AnswerDto answerDto, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        Long answerId = null;
        if (memberDetails.getMember() != null) {
            answerId = answerService.createAnswer(answerDto, memberDetails);
        } else {
            throw new IllegalArgumentException("로그인 후 등록 가능합니다.");
        }

        return answerId;
    }

    @PutMapping("/api/answer")
    public Long updateAnswer(@RequestBody AnswerDto answerDto, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        Long answerId = answerService.RequestBody(answerDto, memberDetails);
        return answerId;
    }

    @DeleteMapping("/api/answer")
    public void deleteAnswer(@RequestBody AnswerDto answerDto, @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        answerService.deleteAnswer(answerDto, memberDetails);
        return;
    }
}
