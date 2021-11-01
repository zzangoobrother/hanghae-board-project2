package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.exception.HanghaeBoardJoinException;
import com.sparta.hanghaeboardproject2.service.MailSendService;
import com.sparta.hanghaeboardproject2.service.MemberService;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private final MailSendService mailSendService;
    private final MemberService memberService;

    public MailController(MailSendService mailSendService, MemberService memberService) {
        this.mailSendService = mailSendService;
        this.memberService = memberService;
    }

    @GetMapping("/emailCheck")
    public void mailCheck(@RequestParam String email) throws Exception {
        System.out.println(email);
        String authKey = mailSendService.sendSimpleMessage(email);
        System.out.println(authKey);
    }

    @GetMapping("/member/signUpConfirm")
    public ResponseEntity<Object> signUpConfirm(@RequestParam String email, @RequestParam String authKey)
        throws HanghaeBoardJoinException, URISyntaxException {
        System.out.println(email);
        System.out.println(authKey);
        memberService.signUpConfirm(email, authKey);

        URI redirectUri = new URI("http://localhost:8080");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(redirectUri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
