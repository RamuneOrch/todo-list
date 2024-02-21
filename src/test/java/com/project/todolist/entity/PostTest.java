package com.project.todolist.entity;

import com.project.todolist.dto.post.PostRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class PostTest {

    Post post;

    @BeforeEach
    public void setUp() {
        post = new Post();
    }

    @Test
    @DisplayName("user 업데이트 테스트")
    void updateUser() {
        // given
        User user = new User();
        // when
        post.updateUser(user);
        // then
    }

    @Test
    @DisplayName("게시물 수정 테스트")
    void update() {
        // given
        String title = "title update";
        String content = "content update";
        PostRequestDto postRequestDto = new PostRequestDto(title, content, true);
        // when
        post.update(postRequestDto);
        // then
    }

    @Test
    @DisplayName("게시물 체크 테스트")
    void isChecked() {
        // given
        // when
        post.isChecked();
        // then
    }
}
