package com.project.todolist.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.project.todolist.Exception.ContentsExistenceException;
import com.project.todolist.dto.post.PostRequestDto;
import com.project.todolist.dto.post.PostResponseDto;
import com.project.todolist.entity.Comment;
import com.project.todolist.entity.Post;
import com.project.todolist.entity.User;
import com.project.todolist.repository.CommentRepository;
import com.project.todolist.repository.PostRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    @BeforeEach
    public void setUp(){
//        MockitoAnnotations.openMocks(this);
    }
    @Nested
    @DisplayName("게시물 작성 테스트")
    class CreatePost{

        @Mock
        User user;
        @Test
        @DisplayName("게시물 작성")
        void createPost() {
            // given
            String title = "title test";
            String content = "content test";
            boolean checkDone = true;

            PostRequestDto postRequestDto = new PostRequestDto(title, content, checkDone);
            // when

            PostResponseDto postResponseDto = postService.createPost(postRequestDto, user);

            // then
            Assertions.assertThat(title).isEqualTo(postResponseDto.getTitle());
            Assertions.assertThat(content).isEqualTo(postResponseDto.getContent());
        }
    }

    @Nested
    @DisplayName("게시물 조회 테스트")
    class GetPost{

        @Mock
        private User user;

        @BeforeEach
        public void setUp(){
            user = new User();
            user.setId(1L);
        }
        @Test
        @DisplayName("게시물 조회 테스트")
        void getPostById() {
            // given
            Long postId = 1L;

            Post post = new Post();
            post.setId(postId);
            post.setTitle("Test Post");
            post.setContent("Test Content");
            post.setCheckDone(true);

            when(postRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(Optional.of(post));

            // when
            PostResponseDto postResponseDto = postService.getPostById(postId, user);

            // then
            assertEquals(postId, postResponseDto.getId());
            System.out.println("postId = " + postId);
            assertEquals(post.getTitle(), postResponseDto.getTitle());
            System.out.println("post.getTitle() = " + post.getTitle());
            System.out.println("postResponseDto.getTitle() = " + postResponseDto.getTitle());
            assertEquals(post.getContent(), postResponseDto.getContent());
        }

        @Test
        @DisplayName("게시물 조회 실패")
        void getPostById_fail() {
            // given
            // 아무 값을 넣어도 상관없음.
            Long invalidPostId = 1L;

            // when
            when(postRepository.findByIdAndUserId(invalidPostId, user.getId())).thenReturn(Optional.empty());

            // then
            assertThrows(ContentsExistenceException.class, () -> {
                postService.getPostById(invalidPostId, user);
            });
        }

        @Test
        @DisplayName("게시물 전체 조회 테스트")
        void getPosts() {
            // given

            List<Post> mockPosts = new ArrayList<>();

            Post post1 = new Post();
            post1.setId(1L);
            post1.setTitle("Test Post1");
            post1.setContent("Test Content1");
            post1.setCheckDone(true);

            Post post2 = new Post();
            post2.setId(2L);
            post2.setTitle("Test Post2");
            post2.setContent("Test Content2");
            post2.setCheckDone(true);

            mockPosts.add(post1);
            mockPosts.add(post2);

            given(postRepository.findAllByUserIdOrderByCheckDoneAscModifiedAtDesc(user.getId())).willReturn(mockPosts);

            // when
            List<PostResponseDto> posts = postService.getPosts(user);

            // then
            assertEquals(2,posts.size());
        }
    }

    @Test
    void checkedPost() {
        // given

        // when

        // then
    }

    @Test
    void updatePostById() {
        // given

        // when

        // then
    }

    @Test
    void deleteById() {
        // given

        // when

        // then
    }
}
