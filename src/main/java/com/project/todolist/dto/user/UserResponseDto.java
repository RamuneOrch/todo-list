package com.project.todolist.dto.user;

import com.project.todolist.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String username;
    private String message;

    public UserResponseDto(String message,User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.message = message;
    }
}
