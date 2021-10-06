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
public class Member {

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

    public Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = null;
    }

    public Member(String username, String password, String email, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.kakaoId = kakaoId;
    }

    public Member(MemberSignupDto memberSignupDto) {
        this.username = memberSignupDto.getUsername();
        this.password = memberSignupDto.getPassword();
        this.email = memberSignupDto.getEmail();
        this.kakaoId = null;
    }
}