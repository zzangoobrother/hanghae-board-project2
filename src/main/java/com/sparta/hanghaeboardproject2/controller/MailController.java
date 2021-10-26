package com.sparta.hanghaeboardproject2.controller;

import com.sparta.hanghaeboardproject2.service.MailSendService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MailController {

    private final MailSendService mailSendService;

    public MailController(MailSendService mailSendService) {
        this.mailSendService = mailSendService;
    }

    @GetMapping("/emailCheck")
    public void mailCheck(@RequestParam String email) throws Exception {
        System.out.println(email);
        String authKey = mailSendService.sendSimpleMessage(email);
        System.out.println(authKey);
    }

    @GetMapping("/member/signUpConfirm")
    public void signUpConfirm(@RequestParam String email, @RequestParam String authKey) {
        System.out.println(email);
        System.out.println(authKey);
    }
}
