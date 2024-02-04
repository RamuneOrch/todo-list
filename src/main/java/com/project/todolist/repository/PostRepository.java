package com.project.todolist.repository;

import com.project.todolist.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findAllByUserIdOrderByCheckDoneAscModifiedAtDesc(long userId);
    Optional<Post> findByIdAndUserId(long postId, long userId);
}
