//package com.project.todolist;
//
//import com.project.todolist.dto.post.PostRequestDto;
//import com.project.todolist.entity.Comment;
//import com.project.todolist.entity.Post;
//import com.project.todolist.entity.User;
//import com.project.todolist.repository.CommentRepository;
//import com.project.todolist.repository.PostRepository;
//import com.project.todolist.repository.UserRepository;
//import com.project.todolist.service.PostService;
//import java.util.List;
//import java.util.Optional;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//@SpringBootTest
//public class DBTest {
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    PostRepository postRepository;
//    @Autowired
//    CommentRepository commentRepository;
//    @Autowired
//    PostService postService;
//
//    @Test
//    @DisplayName("post save 테스트")
//    @Rollback(false)
//    public void postSaveTest() throws Exception {
//        User user = userRepository.findById(4L)
//                .orElseThrow(() -> new NullPointerException("해당 아이디 존재하지 않음"));
//
//        PostRequestDto postRequestDto = new PostRequestDto();
//        for (int i = 0; i < 5; i++) {
//            postRequestDto.setTitle("google 포스트" + i);
//            postRequestDto.setContent("google 콘텐츠" + i);
//            postService.createPost(postRequestDto, user);
//        }
//    }
//
//    @Test
//    @DisplayName("동일한 아이디 post 작성")
//    @Rollback(false)
//    public void postSaveTest2() throws Exception {
//        User user = userRepository.findById(1L)
//                .orElseThrow(() -> new NullPointerException("존재하지 않음."));
//
//        PostRequestDto postRequestDto = new PostRequestDto();
//        postRequestDto.setTitle("test2");
//        postRequestDto.setContent("테스트 도중2");
//        postService.createPost(postRequestDto, user);
//    }
//
//    @Test
//    @DisplayName("회원의 특정 일정 가져오기")
//    public void getTest() throws Exception {
//        // select * from posts where user_id = 1 AND id = 1
//        Post post = postRepository.findByIdAndUserId(1L, 1L)
//                .orElseThrow(() -> new NullPointerException("존재 x"));
//        System.out.println(post.getTitle());
//    }
//
//    @Test
//    @Transactional
//    @DisplayName("회원이 가지고 있는 일정을 수정날짜를 기준으로 내림차순으로 전체 조회")
//    public void getPostAll() {
//        List<Post> postList = postRepository.findAllByUserIdOrderByModifiedAtDesc(2L);
//
//        for (Post post : postList) {
//            System.out.println(post.getTitle());
//        }
//    }
//
//    @Test
//    @Rollback(false)
//    @DisplayName("회원 일정 수정 test")
//    public void updateTest() throws Exception {
//        Post post = postRepository.findByIdAndUserId(1L, 1L)
//                .orElseThrow(() -> new NullPointerException("존재 x"));
//        //given
//        User user = userRepository.findById(1L)
//                .orElseThrow(() -> new NullPointerException("해당 아이디 존재하지 않음"));
//        PostRequestDto req = new PostRequestDto();
//        req.setTitle("수정된 제목");
//        req.setContent("수정된 내용");
//        //when
////        postService.updatePostById(req, 1L, 1L, user);
//        //then
//    }
//
//    @Test
//    @Rollback(false)
//    @DisplayName("특정 일정 삭제 test")
//    public void deleteTest() throws Exception {
//        //given
//        Post post = postRepository.findByIdAndUserId(1L, 1L)
//                .orElseThrow(() -> new NullPointerException("존재 x"));
//        //when
//        postRepository.delete(post);
//        //then
//        Assertions.assertThat(post).isNotNull();
//    }
//
//    @Test
//    @DisplayName("댓글작성 테스트")
//    @Rollback(false)
//    public void commentCreate() throws Exception {
//        //given
//        User user = userRepository.findById(3L).orElseThrow();
//        Post post = postRepository.findByUserId(user.getId()).orElseThrow();
//        Comment comment = new Comment();
//        comment.setComment("papago 댓글");
//        comment.setPost(post);
//        comment.setUser(user);
//        //when
//        commentRepository.save(comment);
//        //then
//    }
//
//    @Test
//    @DisplayName("특정 게시물 댓글 전체 조회")
//    public void getAllComment() throws Exception {
//        //given
//        User user = userRepository.findById(3L).orElseThrow();
//        Post post = postRepository.findByUserId(user.getId()).orElseThrow();
//        List<Comment> commentList = commentRepository.findAllByUserIdAndPostId(
//                user.getId(), post.getId());
//        //when
//        for (Comment comment : commentList) {
//            System.out.println(comment.getComment());
//        }
//        //then
//    }
//
//    @Test
//    @DisplayName("특정 댓글 삭제 테스트")
//    public void deleteCommentTest() throws Exception{
//      //given
//
//      //when
//
//      //then
//    }
//}
