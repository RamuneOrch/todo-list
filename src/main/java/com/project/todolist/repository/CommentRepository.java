package com.project.todolist.repository;

import com.project.todolist.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUserIdAndPostId(Long id, Long id1);
}
