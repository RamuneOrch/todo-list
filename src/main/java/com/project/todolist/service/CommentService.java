package com.project.todolist.service;

import com.project.todolist.Exception.CommentIsEmptyException;
import com.project.todolist.Exception.ContentsExistenceException;
import com.project.todolist.Exception.NotCommentUserException;
import com.project.todolist.dto.comment.CommentRequestDto;
import com.project.todolist.dto.comment.CommentResponseDto;
import com.project.todolist.entity.Comment;
import com.project.todolist.entity.Post;
import com.project.todolist.entity.User;
import com.project.todolist.repository.CommentRepository;
import com.project.todolist.repository.PostRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "댓글 서비스")
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public CommentResponseDto createComment(User user, CommentRequestDto req, Long postId) {
        log.info(user.getUsername());
        Comment comment = new Comment(req);
        Post post = postRepository.findByIdAndUserId(postId, user.getId())
                .orElseThrow(() -> new ContentsExistenceException("존재하지 않아용"));
        comment.updateUser(user);
        comment.updatePost(post);
        commentRepository.save(comment);
        return new CommentResponseDto(user, comment);
    }

    @Transactional
    public CommentResponseDto updateComment(User user, CommentRequestDto req, Long commentId) {
        Comment comment = validateComment(commentId);
        validateUser(user, comment);
        comment.updateComment(req.getComment());
        return new CommentResponseDto(user, comment);
    }

    public String deleteComment(User user, Long commentId) {
        Comment comment = validateComment(commentId);
        validateUser(user, comment);
        commentRepository.delete(comment);
        return "댓글이 삭제되었습니다";
    }

    private Comment validateComment(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentIsEmptyException("댓글이 존재하지 않습니다."));
    }

    private void validateUser(User user, Comment comment) {
        if (!Objects.equals(comment.getUser().getId(), user.getId())) {
            throw new NotCommentUserException("댓글 작성자만 수정하고나 삭제가 가능합니다.");
        }
    }
}
