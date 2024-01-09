package ru.efomenko.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class ExeptionController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError taskNotFoundException(TaskNotFoundException notFoundEventException) {
        return ApiError.builder().message(notFoundEventException.getMessage())
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status(HttpStatus.NOT_FOUND.toString())
                .reason("Task not found")
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError userNotFoundException(UserNotFoundException userNotFound) {
        return ApiError.builder().message(userNotFound.getMessage())
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status(HttpStatus.NOT_FOUND.toString())
                .reason("Task not found")
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ApiError taskValidException(TaskValidFailException taskValidFailException) {
        return ApiError.builder().message(taskValidFailException.getMessage())
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .status(HttpStatus.BAD_GATEWAY.toString())
                .reason("Task valid error")
                .build();
    }
}
