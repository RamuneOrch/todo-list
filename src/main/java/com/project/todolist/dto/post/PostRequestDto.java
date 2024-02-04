package com.project.todolist.dto.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private boolean checkDone;
}
