package com.project.todolist.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.todolist.config.MockSpringSecurityConfig;
import com.project.todolist.config.WebSecurityConfig;
import com.project.todolist.dto.post.PostRequestDto;
import com.project.todolist.entity.User;
import com.project.todolist.security.UserDetailsImpl;
import com.project.todolist.service.PostService;
import java.security.Principal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(
    controllers = PostController.class,
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
public class PostControllerTest {

    private MockMvc mvc;
    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PostService postService;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(springSecurity(new MockSpringSecurityConfig()))
            .build();
    }

    private void mockUserSetup() {
        String username = "qwert1234";
        String password = "qwerQ1234";
        User testUser = new User(username, password);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, null, null);
    }

    @Nested
    @DisplayName("게시물 작성 테스트")
    class createPost {

        @Test
        @DisplayName("게시물 작성 성공")
        public void createPost_success() throws Exception {
            //given
            mockUserSetup();
            String title = "제목";
            String content = "내용";
            boolean checkDone = true;

            PostRequestDto postRequestDto = new PostRequestDto(title, content, checkDone);

            String postInfo = objectMapper.writeValueAsString(postRequestDto);

            // when - then
            mvc.perform(post("/todos/posts")
                    .content(postInfo)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
        }
    }
}
