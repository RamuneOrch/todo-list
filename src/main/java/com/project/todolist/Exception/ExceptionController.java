package com.project.todolist.Exception;

import com.project.todolist.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        return createResponse(HttpStatus.BAD_REQUEST,
                e.getBindingResult().getFieldError().getDefaultMessage());
    }

    private ResponseEntity<ExceptionDto> createResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status.value())
                .body(ExceptionDto.builder()
                        .statusCode(status.value())
                        .state(status)
                        .message(message)
                        .build());
    }
}
