package com.project.todolist.service;

import com.project.todolist.dto.post.PostRequestDto;
import com.project.todolist.dto.post.PostResponseDto;
import com.project.todolist.entity.Post;
import com.project.todolist.entity.User;
import com.project.todolist.repository.PostRepository;
import com.project.todolist.repository.UserRepository;
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
    private final UserRepository userRepository;


    public PostResponseDto createPost(PostRequestDto req, User user) {
        Post post = new Post(req);
        User user1 = findUserById(2L);
        post.setUser(user1);
        postRepository.save(post);
        return new PostResponseDto(post, user1);
    }

    public PostResponseDto getPostById(Long postId, Long userId, User user) {
        Post post = postRepository.findByIdAndUserId(postId, userId)
                .orElseThrow(() -> new NullPointerException("존재 X"));
        User user1 = findUserById(userId);
        return new PostResponseDto(post, user1);
    }

    public List<PostResponseDto> getPosts(Long userId, User user) {
        User user1 = findUserById(userId);
        return postRepository.findAllByUserIdOrderByModifiedAtDesc(userId).stream()
                .map(e -> new PostResponseDto(e, user1)).toList();
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NullPointerException("해당 아이디 존재하지 않음"));
    }

    @Transactional
    public PostResponseDto updatePostById(PostRequestDto req, Long postId, Long userId, User user) {
        User user1 = findUserById(userId);
        Post post = postRepository.findByIdAndUserId(postId, userId)
                .orElseThrow(() -> new NullPointerException("존재 X"));
        post.update(req);
        return new PostResponseDto(post, user1);
    }

    public void deleteById(Long postId, Long userId, User user) {
        Post post = postRepository.findByIdAndUserId(postId, userId)
                .orElseThrow(() -> new NullPointerException("존재 x"));
        postRepository.delete(post);
    }

//    public void deleteAll(Long postId, Long userId, User user) {
//        postRepository.
//    }
}
