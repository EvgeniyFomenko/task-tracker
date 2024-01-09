package ru.efomenko.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.efomenko.dto.TodoListDto;
import ru.efomenko.service.TodoListService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoListController {
    private final TodoListService todoListService;

    @PostMapping("/user/{userId}/todolist/")
    public TodoListDto postTodo(@RequestBody TodoListDto todoListDto, @PathVariable int userId) {
        return todoListService.postTodo(todoListDto, userId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/user/{userId}/todolist/{todoId}")
    public void todoListDeleteById(@PathVariable int userId, @PathVariable int todoId) {
        todoListService.deleteTodo(userId, todoId);
    }

    @GetMapping("/user/{userId}/todolists")
    public List<TodoListDto> getTodos(@PathVariable int userId) {
        return todoListService.getTodos(userId);
    }

    @GetMapping("/user/{userId}/todolist/{todoId}")
    public TodoListDto getTodo(@PathVariable int userId, @PathVariable int todoId) {
        return todoListService.getTodo(userId, todoId);
    }


}
