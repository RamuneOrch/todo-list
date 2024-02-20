package com.project.todolist.dto.post;

import com.project.todolist.entity.Post;
import com.project.todolist.entity.User;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private boolean checkDone;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Map<Long, String> comments;

    public PostResponseDto(Post post, User user) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = user.getUsername();
        this.checkDone = post.isCheckDone();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public PostResponseDto(Post post, User user, Map<Long, String> comments) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.username = user.getUsername();
        this.comments = comments;
        this.checkDone = post.isCheckDone();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }
}
