package com.project.todolist.controller;

import com.project.todolist.dto.ResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class CommentController {

    @PostMapping("/comment")
    public ResponseDto createComment(){
        return new ResponseDto();
    }
}
