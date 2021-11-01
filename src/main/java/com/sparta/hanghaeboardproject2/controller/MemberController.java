package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.dto.MemberSignupDto;
import com.sparta.hanghaeboardproject2.exception.HanghaeBoardJoinException;
import com.sparta.hanghaeboardproject2.security.MemberDetailsImpl;
import com.sparta.hanghaeboardproject2.service.MailSendService;
import com.sparta.hanghaeboardproject2.service.MemberService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    private final MemberService memberService;
    private final MailSendService mailSendService;

    public MemberController(MemberService memberService, MailSendService mailSendService) {
        this.memberService = memberService;
        this.mailSendService = mailSendService;
    }

    @GetMapping("/member/signup")
    public String getMemberForm(@AuthenticationPrincipal MemberDetailsImpl memberDetails, @RequestParam(required = false) String msg, Model model) {
        if (memberDetails != null) {
            return "redirect:/";
        }

        MemberSignupDto memberSignupDto = new MemberSignupDto();
        model.addAttribute("memberSignupDto" , memberSignupDto);
        model.addAttribute("msg", msg);
        return "memberJoinForm";
    }

    @PostMapping("/member/signup")
    public String memberJoin(@ModelAttribute @Validated MemberSignupDto memberSignupDto, BindingResult result)
        throws Exception {
        if (result.hasErrors()) {
            return "memberJoinForm";
        }
        String authKey = mailSendService.sendSimpleMessage(memberSignupDto.getEmail());
        memberService.memberJoin(memberSignupDto, authKey);

        return "redirect:/member/login";
    }

    @GetMapping("/member/login")
    public String login(@AuthenticationPrincipal MemberDetailsImpl memberDetails, @RequestParam(required = false) String msg, Model model) {
        if (memberDetails != null) {
            return "redirect:/";
        }
        model.addAttribute("msg", msg);
        return "memberLogin";
    }
}
