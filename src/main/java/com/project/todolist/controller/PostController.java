package com.project.todolist.controller;

import com.project.todolist.dto.post.PostRequestDto;
import com.project.todolist.dto.post.PostResponseDto;
import com.project.todolist.security.UserDetailsImpl;
import com.project.todolist.service.CommonResponse;
import com.project.todolist.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<CommonResponse<PostResponseDto>> createPost(
            @RequestBody PostRequestDto req, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok()
                .body(CommonResponse.<PostResponseDto>builder().statusCode(HttpStatus.OK.value())
                        .msg("일정 생성 완료")
                        .data(postService.createPost(req, userDetails.getUser()))
                        .build()
                );
    }

    @GetMapping("/posts/postId/{postId}")
    public ResponseEntity<CommonResponse<PostResponseDto>> getPostById(
            @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        return ResponseEntity.ok()
                .body(CommonResponse.<PostResponseDto>builder().statusCode(HttpStatus.OK.value())
                        .msg("특정 일정 조회 완료")
                        .data(postService.getPostById(postId, userDetails.getUser()))
                        .build()
                );
    }

    @GetMapping("/posts")
    public ResponseEntity<CommonResponse<List<PostResponseDto>>> getPosts(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok()
                .body(CommonResponse.<List<PostResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("회원의 일정 전체 조회")
                        .data(postService.getPosts(userDetails.getUser()))
                        .build()
                );
    }

    @PutMapping("/posts/postId/{postId}")
    public ResponseEntity<CommonResponse<PostResponseDto>> checkedPost(
            @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        return ResponseEntity.ok()
                .body(CommonResponse.<PostResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("일정 완료 버튼")
                        .data(postService.checkedPost(postId, userDetails.getUser()))
                        .build()
                );
    }

    @PatchMapping("/posts/postId/{postId}")
    public ResponseEntity<CommonResponse<PostResponseDto>> updatePostById(
            @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId,
            @RequestBody PostRequestDto req) {
        return ResponseEntity.ok()
                .body(CommonResponse.<PostResponseDto>builder().statusCode(HttpStatus.OK.value())
                        .msg("특정 일정 수정 완료")
                        .data(postService.updatePostById(req, postId, userDetails.getUser()))
                        .build()
                );
    }

    @DeleteMapping("/posts/postId/{postId}")
    public ResponseEntity<CommonResponse<Void>> deleteById(
            @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        try {
            postService.deleteById(postId, userDetails.getUser());

            CommonResponse.<PostResponseDto>builder()
                    .statusCode(HttpStatus.OK.value())
                    .msg("특정 일정 삭제 완료")
                    .build();

        } catch (IllegalArgumentException e) {

            CommonResponse.<PostResponseDto>builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .msg(e.getMessage())
                    .build();
        }
        return ResponseEntity.noContent().build();
    }

}
