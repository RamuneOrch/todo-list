package com.project.todolist.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentIsEmptyException extends RuntimeException {

    public CommentIsEmptyException(String message) {
        super(message);
    }
}
