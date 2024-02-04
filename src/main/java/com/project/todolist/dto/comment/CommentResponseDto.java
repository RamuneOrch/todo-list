package com.project.todolist.dto.comment;

import com.project.todolist.entity.Comment;
import com.project.todolist.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String username;
    private String comment;

    public CommentResponseDto(User user, Comment comment){
        this.id = comment.getId();
        this.username = user.getUsername();
        this.comment = comment.getComment();
    }
}
