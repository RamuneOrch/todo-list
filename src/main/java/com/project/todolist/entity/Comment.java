package com.project.todolist.entity;

import com.project.todolist.dto.comment.CommentRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public void updateUser(User user){
        this.user = user;
    }

    public void updatePost(Post post){
        this.post = post;
    }

    public void updateComment(String comment){
        this.comment = comment;
    }

    public Comment(CommentRequestDto req) {
        this.comment = req.getComment();
    }
}
