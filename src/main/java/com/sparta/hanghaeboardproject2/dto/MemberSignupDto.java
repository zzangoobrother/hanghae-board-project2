package com.sparta.hanghaeboardproject2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
public class MemberSignupDto {

    @NotBlank(message = "ID를 입력해주세요!")
    @Pattern(regexp = "^[a-zA-Z0-9]{3,}$", message = "ID는 최소 3자 이상, 알파벳, 숫자 조합으로 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요!")
    @Pattern(regexp = "^[!@#*^a-zA-Z0-9]{4,}$", message = "비밀번호는 최소 4자  이상 입니다.")
    private String password;

    private String passwordOnemore;

    @NotBlank(message = "이메일을 입력해주세요!")
    @Email(message = "이메일 양식을 지켜주세요!")
    private String email;

    public MemberSignupDto(String username, String password, String passwordOnemore, String email) {
        this.username = username;
        this.password = password;
        this.passwordOnemore = passwordOnemore;
        this.email = email;
    }
}
