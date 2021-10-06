package com.sparta.hanghaeboardproject2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.hanghaeboardproject2.service.MemberKakaoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberKakaoController {

    private final MemberKakaoService memberKakaoService;

    public MemberKakaoController(MemberKakaoService memberKakaoService) {
        this.memberKakaoService = memberKakaoService;
    }

    @GetMapping("/member/kakao/callback")
    public String memberKakaoLogin(@RequestParam String code) throws JsonProcessingException {
        memberKakaoService.memberKakaoLogin(code);
        return "redirect:/";
    }
}
