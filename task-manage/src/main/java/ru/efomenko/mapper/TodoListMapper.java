package ru.efomenko.mapper;

import ru.efomenko.dto.TodoListDto;
import ru.efomenko.entity.TodoList;

public class TodoListMapper {
    public static TodoList toTodoList(TodoListDto todoListDto) {
        return TodoList.builder().tasks(todoListDto.getTask())
                .id(todoListDto.getId())
                .name(todoListDto.getName())
                .tasks(todoListDto.getTask())
                .build();
    }

    public static TodoListDto toTodoListDto(TodoList todoList) {
        return TodoListDto.builder().name(todoList.getName())
                .id(todoList.getId())
                .task(todoList.getTasks())
                .name(todoList.getName())
                .build();
    }
}
