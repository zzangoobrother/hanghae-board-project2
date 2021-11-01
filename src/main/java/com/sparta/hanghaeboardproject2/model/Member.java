package com.sparta.hanghaeboardproject2.model;

import com.sparta.hanghaeboardproject2.dto.MemberSignupDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Member extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private Long kakaoId;

    @Column(nullable = false)
    private String authKey;

    @Column(nullable = false)
    private boolean authCheck;

    public Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = null;
        this.authKey = "";
        this.authCheck = false;
    }

    public Member(String username, String password, String email, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
        this.authKey = "";
        this.authCheck = true;
    }

    public Member(MemberSignupDto memberSignupDto, String authKey) {
        this.username = memberSignupDto.getUsername();
        this.password = memberSignupDto.getPassword();
        this.email = memberSignupDto.getEmail();
        this.kakaoId = null;
        this.authKey = authKey;
        this.authCheck = false;
    }

    public Member confirm() {
        this.authKey = "";
        this.authCheck = true;
        return this;
    }
}