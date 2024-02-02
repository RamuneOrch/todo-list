package com.project.todolist.repository;

import com.project.todolist.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUserIdAndPostId(Long id, Long id1);

    Optional<Comment> findByUserIdAndId(Long userId ,Long commentId);
}
