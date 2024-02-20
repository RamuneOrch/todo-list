package com.project.todolist.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.project.todolist.entity.Post;
import com.project.todolist.entity.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource("classpath:application-test.yml")
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    User user;

    @BeforeEach
    public void setUp() {
        user = User.builder().id(1L).build();
    }

    @Test
    @DisplayName("user가 작성한 게시물 리스트 내림차순으로 조회")
    void findPostAll() {
        // given
        List<Post> posts = new ArrayList<>();
        Post post1 = Post.builder()
            .title("title")
            .content("content")
            .checkDone(true)
            .build();
        Post post2 = Post.builder()
            .title("title")
            .content("content")
            .checkDone(true)
            .build();
        post1.updateUser(user);
        post2.updateUser(user);
        userRepository.save(user);
        Post savePost1 = postRepository.save(post1);
        Post savePost2 = postRepository.save(post2);
        posts.add(savePost1);
        posts.add(savePost2);
        // when
        List<Post> savePosts = postRepository.findAllByUserIdOrderByCheckDoneAscModifiedAtDesc(
            user.getId());
        // then
        assertEquals(posts.size(), savePosts.size());
    }

    @Test
    @DisplayName("user가 작성한 게시물 조회")
    void findPost() {
        // given
        Post post = Post.builder()
            .title("title")
            .content("content")
            .checkDone(true)
            .user(user)
            .build();
        userRepository.save(user);
        postRepository.save(post);
        // when
        Post savePost = postRepository.findByIdAndUserId(post.getId(), user.getId()).orElseThrow(() -> new NullPointerException("존재 x"));
        // then
        assertEquals(post.getId(),savePost.getId());
        System.out.println("post.getId() = " + post.getId());
        System.out.println("savePost.getId() = " + savePost.getId());
    }
}
