package com.project.todolist.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.project.todolist.Exception.ContentsExistenceException;
import com.project.todolist.dto.post.PostRequestDto;
import com.project.todolist.dto.post.PostResponseDto;
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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @InjectMocks
    private PostService postService;

    @Mock
    private PostRepository postRepository;

    @Mock
    private CommentRepository commentRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = User.builder().id(1L).build();
    }

    @Nested
    @DisplayName("게시물 작성")
    class CreatePost {
        @Test
        @DisplayName("게시물 작성 성공")
        void createPost() {
            // given
            String title = "title test";
            String content = "content test";
            boolean checkDone = true;

            PostRequestDto postRequestDto = new PostRequestDto(title, content, checkDone);

//            given(postRepository.save(post)).willReturn(post);

            given(postRepository.save(any(Post.class))).willAnswer(
                invocation -> invocation.<Post>getArgument(0));

            // when

            PostResponseDto postResponseDto = postService.createPost(postRequestDto, user);

            // then
            // if( title.equals(postResponseDto.getTitle() )
            Assertions.assertThat(title).isEqualTo(postResponseDto.getTitle());
            Assertions.assertThat(content).isEqualTo(postResponseDto.getContent());
        }
    }

    @Nested
    @DisplayName("게시물 조회")
    class GetPost {

        @Test
        @DisplayName("게시물 조회 성공")
        void getPostById() {
            // given
            Long postId = 1L;

            Post post = new Post();
            ReflectionTestUtils.setField(post,"title","test Post1");
            ReflectionTestUtils.setField(post,"id",1L);
            ReflectionTestUtils.setField(post,"content","content post");
            ReflectionTestUtils.setField(post,"checkDone",true);

            given(postRepository.findByIdAndUserId(postId, user.getId())).willReturn(
                Optional.of(post));
            given(commentRepository.findAllByPostId(postId)).willReturn(Collections.emptyList());

            // when
            PostResponseDto postResponseDto = postService.getPostById(postId, user);

            // then
            assertEquals(postId, postResponseDto.getId(),
                "예상 값: " + postId + ", 실제 값: " + postResponseDto.getId()
                );
            assertEquals(post.getTitle(), postResponseDto.getTitle());
            assertEquals(post.getContent(), postResponseDto.getContent());
        }

        @Test
        @DisplayName("게시물 조회 실패")
        void getPostById_fail() {
            // given
            // 아무 값을 넣어도 상관없음.
            Long invalidPostId = 999L;

            given(postRepository.findByIdAndUserId(invalidPostId, user.getId())).willReturn(
                Optional.empty());
            // when + then
            assertThrows(ContentsExistenceException.class, () -> {
                postService.getPostById(invalidPostId, user);
            });
        }

        @Test
        @DisplayName("게시물 전체 조회 성공")
        void getPosts() {
            // given

            List<Post> mockPosts = new ArrayList<>();

            Post post1 = Post
                .builder()
                .id(1L)
                .title("Test Post1")
                .content("Test Content1")
                .checkDone(true)
                .build();

            Post post2 = Post
                .builder()
                .id(2L)
                .title("Test Post2")
                .content("Test Content2")
                .checkDone(true)
                .build();

            mockPosts.add(post1);
            mockPosts.add(post2);

            given(postRepository.findAllByUserIdOrderByCheckDoneAscModifiedAtDesc(
                user.getId())).willReturn(mockPosts);

            // when
            List<PostResponseDto> posts = postService.getPosts(user);

            // then
            assertEquals(2, posts.size());
        }
    }

    @Nested
    @DisplayName("게시물 수정")
    class UpdatePost {

        Long postId;
        String title;
        String content;
        boolean checkDone;

        @BeforeEach
        public void updateSetUp() {
            postId = 1L;
            title = "title update";
            content = "content update";
            checkDone = true;
        }

        @Test
        @DisplayName("게시물 수정 성공")
        void updatePost() {
            // given

            PostRequestDto postRequestDto = new PostRequestDto(title, content, checkDone);

            Post post = new Post(postRequestDto);

            given(postRepository.findByIdAndUserId(postId, user.getId())).willReturn(
                Optional.of(post));

            // when
            PostResponseDto postResponseDto = postService.updatePostById(postRequestDto, postId,
                user);

            // then
            assertEquals(title, postResponseDto.getTitle());

        }

        @Test
        @DisplayName("게시물 수정 실패")
        public void updatePost_fail() throws Exception {
            //given
            Long postId = 999L;

            PostRequestDto postRequestDto = new PostRequestDto(title, content, checkDone);

            //when + then
            assertThrows(ContentsExistenceException.class, () -> {
                postService.updatePostById(postRequestDto, postId, user);
            });
        }
    }

    @Nested
    @DisplayName("게시물 삭제")
    class DeletePost {

        Long postId;

        @BeforeEach
        public void deleteSetUp() {
            postId = 1L;
        }

        @Test
        @DisplayName("게시물 삭제 성공")
        void deletePost() {
            // given
            Post post = new Post();

            given(postRepository.findByIdAndUserId(postId, user.getId())).willReturn(
                Optional.of(post));
            // when
            postService.deleteById(postId, user);
            // then
        }

        @Test
        @DisplayName("게시물 삭제 실패")
        public void deletePost_fail() throws Exception {
            //when + then
            assertThrows(ContentsExistenceException.class, () -> {
                postService.deleteById(postId, user);
            });
        }
    }
}
