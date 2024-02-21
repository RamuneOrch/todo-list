package com.project.todolist.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.todolist.config.MockSpringSecurityConfig;
import com.project.todolist.config.WebSecurityConfig;
import com.project.todolist.dto.user.UserRequestDto;
import com.project.todolist.entity.User;
import com.project.todolist.filter.JwtAuthenticationFilter;
import com.project.todolist.repository.UserRepository;
import com.project.todolist.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(
    controllers = UserController.class,
    excludeFilters = {
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = WebSecurityConfig.class
        )
    }
)
class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .addFilter(jwtAuthenticationFilter)
            .apply(springSecurity(new MockSpringSecurityConfig()))
            .build();

        String username = "song1234";
        String password = "qwerQ1234";
        User testUser = new User(username, password);
        userRepository.save(testUser);
    }

    @Test
    @DisplayName("회원 가입 요청 처리")
    void signUp() throws Exception {
        // given
        String username = "song1234@naver.com";
        String password = "qwerQ1234";

        UserRequestDto userRequestDto = new UserRequestDto(username, password);

        String user = objectMapper.writeValueAsString(userRequestDto);

        // when - then
        mvc.perform(post("/user/signup")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andDo(print());
    }

    @Test
    @DisplayName("회원 가입 요청 실패")
    void signUp_fail() throws Exception {
        // given
        String username = "song1234";
        String password = "qwer1234";

        UserRequestDto userRequestDto = new UserRequestDto(username, password);

        String user = objectMapper.writeValueAsString(userRequestDto);

        // when - then
        mvc.perform(post("/user/signup")
                .content(user)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().is4xxClientError())
            .andDo(print());
    }
}
