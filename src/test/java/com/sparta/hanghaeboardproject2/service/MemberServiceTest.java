package com.sparta.hanghaeboardproject2.service;

import com.sparta.hanghaeboardproject2.dto.MemberSignupDto;
import com.sparta.hanghaeboardproject2.exception.HanghaeBoardJoinException;
import com.sparta.hanghaeboardproject2.model.Member;
import com.sparta.hanghaeboardproject2.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    MemberService memberService;

    @BeforeEach
    public void setup() {

    }

    @Test
    @DisplayName("닉네임 최소 3자 이상, 알파벳 대소문자, 숫자로 구성 성공")
    void nicknameCheck_Normal() {
        MemberSignupDto memberSignupDto = new MemberSignupDto();

        memberSignupDto.setUsername("hongkildong");

        assertThat("hongkildong").isEqualTo(memberSignupDto.getUsername());
    }

    @Test
    @DisplayName("닉네임 구성 실패")
    void nicknameCheck_Failed() throws Exception{

    }

    @Test
    @DisplayName("비밀번호 최소 4자 이상")
    void passwordCheck_Failed() {

    }

    @Test
    @DisplayName("비밀번호에 닉네임과 같은 단어 포함")
    void passwordSameNickname() {

    }

    @Test
    @DisplayName("회원가입 완료")
    void memeberJoin_Normal() throws HanghaeBoardJoinException {
        MemberSignupDto memberSignupDto = new MemberSignupDto("홍길동", "1234567", "1234567", "sss@naver.com");
        Member member = new Member(memberSignupDto);

        when(memberRepository.save(any())).thenReturn(member);

        Member saveMember = memberService.memberJoin(memberSignupDto);

        assertThat(saveMember.getEmail()).isEqualTo(member.getEmail());
        assertThat(saveMember.getUsername()).isEqualTo(member.getUsername());
    }

    @Test
    @DisplayName("존재하는 회원")
    void memeberSame_Failed() {

    }

    @Test
    @DisplayName("중복된 닉네임 검사")
    void nicknameSame_Failed() {

    }
}
