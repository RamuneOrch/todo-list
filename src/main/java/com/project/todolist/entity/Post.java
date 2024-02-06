package com.project.todolist.entity;

import com.project.todolist.dto.post.PostRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "posts")
@NoArgsConstructor
public class Post extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private boolean checkDone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void updateUser(User user){
        this.user = user;
    }

    public Post(PostRequestDto req) {
        this.title = req.getTitle();
        this.content = req.getContent();
    }

    public void update(PostRequestDto req) {
        if (req.getTitle() != null) {
            this.title = req.getTitle();
        }
        if (req.getContent() != null) {
            this.content = req.getContent();
        }
        this.checkDone = req.isCheckDone();
    }
}
