package com.project.todolist.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContentsExistenceException extends NullPointerException{
    public ContentsExistenceException(String message){
        super(message);
    }
}