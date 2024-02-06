package com.project.todolist.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserExistenceException extends RuntimeException {

    public UserExistenceException(String message) {
        super(message);
    }
}
