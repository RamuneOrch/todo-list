package com.project.todolist.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {

    @NotNull(message = "username을 입력해주세요")
    @Pattern(regexp = "^[a-z0-9]+$")
    @Size(min = 4, max = 10)
    private String username;


    @NotNull(message = "password를 입력해주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    @Size(min = 8, max = 15)
    private String password;
}
