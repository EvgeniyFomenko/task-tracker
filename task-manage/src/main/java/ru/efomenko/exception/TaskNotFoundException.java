package ru.efomenko.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String format) {
        super(format);
    }
}
