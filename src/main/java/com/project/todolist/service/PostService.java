package com.project.todolist.service;

import com.project.todolist.dto.post.PostRequestDto;
import com.project.todolist.dto.post.PostResponseDto;
import com.project.todolist.entity.Post;
import com.project.todolist.entity.User;
import com.project.todolist.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PostService")
public class PostService {

    private final PostRepository postRepository;


    public PostResponseDto createPost(PostRequestDto req, User user) {
        Post post = new Post(req);
        post.setUser(user);
        log.info(String.valueOf(user.getId()));
        postRepository.save(post);
        return new PostResponseDto(post, user);
    }

    public PostResponseDto getPostById(Long postId, User user) {
        Post post = postRepository.findByIdAndUserId(postId, user.getId())
                .orElseThrow(() -> new NullPointerException("존재 X"));
        return new PostResponseDto(post, user);
    }

    public List<PostResponseDto> getPosts(User user) {
        return postRepository.findAllByUserIdOrderByCheckDoneAscModifiedAtDesc(user.getId()).stream()
                .map(e -> new PostResponseDto(e, user)).toList();
    }

    @Transactional
    public PostResponseDto updatePostById(PostRequestDto req, Long postId, User user) {
        Post post = postRepository.findByIdAndUserId(postId, user.getId())
                .orElseThrow(() -> new NullPointerException("존재 X"));
        post.update(req);
        return new PostResponseDto(post, user);
    }

    public void deleteById(Long postId, User user) {
        Post post = postRepository.findByIdAndUserId(postId, user.getId())
                .orElseThrow(() -> new NullPointerException("존재 x"));
        postRepository.delete(post);
    }

//    public void deleteAll(Long postId, Long userId, User user) {
//        postRepository.
//    }
}
