package com.sparta.hanghaeboardproject2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class MailSendService {

    @Autowired
    private JavaMailSender javaMailSender;

    public static final String ePw = createKey();

    private static String createKey() {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) ((int) (random.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(10));
                    break;
            }
        }

        return key.toString();
    }

    public String sendSimpleMessage(String to) throws Exception {
        MimeMessage message = createMessage(to);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        }

        return ePw;
    }

    private MimeMessage createMessage(String to) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, to);
        message.setSubject("TEST 회원가입 이메일 인증");

        String mes = "";
        mes+= "<div style='margin:100px;'>";
        mes+= "<h1> 안녕하세요 Babble입니다. </h1>";
        mes+= "<br>";
        mes+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
        mes+= "<br>";
        mes+= "<p>감사합니다!<p>";
        mes+= "<br>";
        mes+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        mes+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        mes+= "<div style='font-size:130%'>";
        mes+= "CODE : <strong>";
        mes+= "<a href='http://localhost:8080/member/signUpConfirm?email=";
        mes += to;
        mes += "&authKey=";
        mes += ePw;
        mes += "' target='_blenk'>이메일 인증</a></strong><div><br/>";
        mes+= "</div>";
        message.setText(mes, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("choiseonkang@gmail.com","Babble"));

        return message;
    }
}
