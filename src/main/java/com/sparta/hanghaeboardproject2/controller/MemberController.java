package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.dto.MemberSignupDto;
import com.sparta.hanghaeboardproject2.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/signup")
    public String getMemberForm() {
        return "memberJoinForm";
    }

    @PostMapping("/member/signup")
    public String memberJoin(@ModelAttribute @Validated MemberSignupDto memberSignupDto, BindingResult result) {
        if (result.hasErrors()) {
            return "memberJoinForm";
        }
        memberService.memberJoin(memberSignupDto);
        return "redirect:/member/login";
    }
}
