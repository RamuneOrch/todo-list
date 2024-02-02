package com.project.todolist.controller;

import com.project.todolist.dto.ResponseDto;
import com.project.todolist.dto.comment.CommentRequestDto;
import com.project.todolist.dto.comment.CommentResponseDto;
import com.project.todolist.security.UserDetailsImpl;
import com.project.todolist.service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/postId/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody CommentRequestDto req,@PathVariable Long postId){
        return ResponseEntity.ok().body(commentService.createComment(user.getUser(),req, postId));
    }

    @PutMapping("/comment/commentId/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody CommentRequestDto req,@PathVariable Long commentId){
        return ResponseEntity.ok().body(commentService.updateComment(user.getUser(),req,commentId));
    }

    @DeleteMapping("/comment/commentId/{commentId}")
    public ResponseEntity<String> deleteComment(@AuthenticationPrincipal UserDetailsImpl user,@PathVariable Long commentId){
        return ResponseEntity.ok().body(commentService.deleteComment(user.getUser(),commentId));
    }
}
