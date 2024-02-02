package com.project.todolist.controller;

import com.project.todolist.dto.ResponseDto;
import com.project.todolist.dto.comment.CommentRequestDto;
import com.project.todolist.dto.comment.CommentResponseDto;
import com.project.todolist.security.UserDetailsImpl;
import com.project.todolist.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/posts/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody CommentRequestDto req,@PathVariable Long postId){
        return ResponseEntity.ok().body(commentService.createComment(user.getUser(),req, postId));
    }
}
