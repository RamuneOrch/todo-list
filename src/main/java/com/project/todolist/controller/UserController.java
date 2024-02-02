package com.project.todolist.controller;

import com.project.todolist.dto.user.UserRequestDto;
import com.project.todolist.dto.user.UserResponseDto;
import com.project.todolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/user/signup")
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto req){
        return ResponseEntity.ok().body(userService.createUser(req));
    }
}
