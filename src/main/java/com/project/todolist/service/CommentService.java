package com.project.todolist.service;

import com.project.todolist.dto.ResponseDto;
import com.project.todolist.dto.comment.CommentRequestDto;
import com.project.todolist.dto.comment.CommentResponseDto;
import com.project.todolist.entity.Comment;
import com.project.todolist.entity.Post;
import com.project.todolist.entity.User;
import com.project.todolist.repository.CommentRepository;
import com.project.todolist.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "댓글 서비스")
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public CommentResponseDto createComment(User user, CommentRequestDto req, Long postId) {
        log.info(user.getUsername());
        Comment comment = new Comment(req);
        Post post = postRepository.findByIdAndUserId(postId,user.getId()).orElseThrow(() -> new NullPointerException("존재하지 않아용"));
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return new CommentResponseDto(user, comment);
    }
}
