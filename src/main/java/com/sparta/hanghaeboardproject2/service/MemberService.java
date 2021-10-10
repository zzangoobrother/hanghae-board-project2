package com.sparta.hanghaeboardproject2.service;

import com.sparta.hanghaeboardproject2.dto.MemberSignupDto;
import com.sparta.hanghaeboardproject2.exception.HanghaeBoardJoinException;
import com.sparta.hanghaeboardproject2.exception.HanghaeBoardLoginException;
import com.sparta.hanghaeboardproject2.model.Member;
import com.sparta.hanghaeboardproject2.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Member memberJoin(MemberSignupDto memberSignupDto) throws HanghaeBoardJoinException {
        // 중복 id 확인
        String username = memberSignupDto.getUsername();
        Optional<Member> foundMember = memberRepository.findByUsername(username);
        if (foundMember.isPresent()) {
            throw new HanghaeBoardJoinException("중복된 ID가 존재합니다.");
        }

        if (memberSignupDto.getPassword().contains(memberSignupDto.getUsername())) {
            throw new HanghaeBoardJoinException("비밀번호에 아이디가 포함될 수 없습니다.");
        }

        if (!memberSignupDto.getPassword().equals(memberSignupDto.getPasswordOnemore())) {
            throw new HanghaeBoardJoinException("비밀번호가 일치 하지 않습니다.");
        }

        //패스워드 암호화
        String password = passwordEncoder.encode(memberSignupDto.getPassword());
        String email = memberSignupDto.getEmail();

        Member member = new Member(username, password, email);

        return memberRepository.save(member);
    }
}
