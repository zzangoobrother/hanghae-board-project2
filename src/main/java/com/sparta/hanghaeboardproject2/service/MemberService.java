package com.sparta.hanghaeboardproject2.service;

import com.sparta.hanghaeboardproject2.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private static final String ADMIN_TOKEN = "";

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
