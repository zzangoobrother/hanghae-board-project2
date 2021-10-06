package com.sparta.hanghaeboardproject2.service;

import com.sparta.hanghaeboardproject2.controller.MemberController;
import com.sparta.hanghaeboardproject2.dto.MemberSignupDto;
import com.sparta.hanghaeboardproject2.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @Autowired
    MemberController memberController;

    private MockMvc mock;

    @BeforeEach
    public void setup() {
        mock = MockMvcBuilders.standaloneSetup(memberController).build();
        System.out.println("ddd" + mock.getClass());
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
        mock.perform(post("/member/signup")
                .param("username", "dd")
                .param("password", "123456")
                .param("email", "sss"))
                .andExpect(status().isOk())
                .andExpect(model().errorCount(1))
                .andExpect(model().attributeHasFieldErrorCode("memberSignupDto", "username", ""))
                .andExpect(model().attributeHasFieldErrorCode("memberSignupDto", "email", ""));

        //assertThat("ID는 최소 3자 이상, 알파벳, 숫자 조합으로 입력해주세요.").isEqualTo(exception.getMessage());
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
    void memeberJoin_Normal() {

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
