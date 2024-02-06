package com.project.todolist.service;

import com.project.todolist.Exception.ContentsExistenceException;
import com.project.todolist.dto.post.PostRequestDto;
import com.project.todolist.dto.post.PostResponseDto;
import com.project.todolist.entity.Comment;
import com.project.todolist.entity.Post;
import com.project.todolist.entity.User;
import com.project.todolist.repository.CommentRepository;
import com.project.todolist.repository.PostRepository;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PostService")
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;


    public PostResponseDto createPost(PostRequestDto req, User user) {
        Post post = new Post(req);
        post.updateUser(user);
        log.info(String.valueOf(user.getId()));
        postRepository.save(post);
        return new PostResponseDto(post, user);
    }

    public PostResponseDto getPostById(Long postId, User user) {
        Post post = getPost(postId, user);
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        return new PostResponseDto(post, user, getCommentList(comments));
    }

    private Post getPost(Long postId, User user) {
        Post post = postRepository.findByIdAndUserId(postId, user.getId())
                .orElseThrow(() -> new ContentsExistenceException("해당 값이 존재하지 않습니다"));
        return post;
    }

    private Map<Long, String> getCommentList(List<Comment> comments) {
        Map<Long, String> commentList = new LinkedHashMap<>();
        for (Comment comment : comments) {
            commentList.put(comment.getId(), comment.getComment());
        }
        return commentList;
    }

    public List<PostResponseDto> getPosts(User user) {
        return postRepository.findAllByUserIdOrderByCheckDoneAscModifiedAtDesc(user.getId())
                .stream()
                .map(e -> new PostResponseDto(e, user)).toList();
    }

    @Transactional
    public PostResponseDto updatePostById(PostRequestDto req, Long postId, User user) {
        Post post = getPost(postId, user);
        post.update(req);
        return new PostResponseDto(post, user);
    }

    public void deleteById(Long postId, User user) {
        Post post = getPost(postId, user);
        postRepository.delete(post);
    }
}
