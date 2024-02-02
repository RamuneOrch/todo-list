package com.project.todolist.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotCommentUserException extends RuntimeException{
    public NotCommentUserException(String message){
        super(message);
    }
}
