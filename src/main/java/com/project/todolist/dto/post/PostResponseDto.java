package com.project.todolist.dto.post;

import com.project.todolist.entity.Post;
import com.project.todolist.entity.TimeStamped;
import com.project.todolist.entity.User;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto{
    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post, User user){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = user.getUsername();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
