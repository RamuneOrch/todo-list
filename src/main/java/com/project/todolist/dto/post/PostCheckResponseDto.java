package com.project.todolist.dto.post;

import com.project.todolist.entity.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCheckResponseDto {
    private boolean isChecked;
    public PostCheckResponseDto(Post post){

    }
}
