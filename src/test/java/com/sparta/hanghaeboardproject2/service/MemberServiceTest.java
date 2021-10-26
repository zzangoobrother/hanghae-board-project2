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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    HanghaeBoardJoinException hanghaeBoardJoinException;

    @BeforeEach
    public void setup() {
        Member member =new Member("길동", "1234567", "aa@naver.com");
        memberRepository.save(member);
    }

    @Test
    @DisplayName("닉네임 최소 3자 이상, 알파벳 대소문자, 숫자로 구성 성공")
    void nicknameCheck_Normal() {

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
    void memeberSame_Failed() throws HanghaeBoardJoinException {
        MemberSignupDto memberSignupDto = new MemberSignupDto("길동", "1234567", "1234567", "aa@naver.com");

        willThrow(HanghaeBoardJoinException.class).given(memberService).memberJoin(memberSignupDto);

        assertThrows(HanghaeBoardJoinException.class, () -> memberService.memberJoin(memberSignupDto));

        verify(memberRepository, never()).save(any(Member.class));
    }

    @Test
    @DisplayName("중복된 닉네임 검사")
    void nicknameSame_Failed() {

    }
}
