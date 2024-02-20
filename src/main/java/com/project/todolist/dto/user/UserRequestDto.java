package com.project.todolist.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @Email(message = "이메일 형식으로 입력해주세요")
    @NotNull(message = "username을 입력해주세요")
    @Size(min = 4, max = 20, message = "최소 4글자 이상 20글자 이하여야합니다")
    private String username;


    @NotNull(message = "password를 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]+$", message = "영어 소문자와 대문자, 숫자는 무조건 1글자 이상 포함되어야합니다")
    @Size(min = 8, max = 15, message = "최소 8글자 이상 15글자 이하여야합니다.")
    private String password;
}
