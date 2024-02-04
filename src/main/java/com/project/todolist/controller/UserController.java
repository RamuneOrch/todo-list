package com.project.todolist.controller;

import com.project.todolist.dto.user.UserRequestDto;
import com.project.todolist.dto.user.UserResponseDto;
import com.project.todolist.service.UserService;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserRequestDto req, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> signUpErrorList = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String defaultError = fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage();
                signUpErrorList.add(defaultError);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(signUpErrorList);
        }
        return ResponseEntity.ok().body(userService.createUser(req));
    }
}
