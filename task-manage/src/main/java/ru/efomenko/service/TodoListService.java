package ru.efomenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.efomenko.dto.TodoListDto;
import ru.efomenko.entity.TodoList;
import ru.efomenko.entity.User;
import ru.efomenko.exception.UserNotFoundException;
import ru.efomenko.mapper.TodoListMapper;
import ru.efomenko.repository.TodoListRepository;
import ru.efomenko.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    public TodoListDto postTodo(TodoListDto todoListDto, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        TodoList todoList = TodoListMapper.toTodoList(todoListDto);
        todoList.setOwner(user);
        return TodoListMapper.toTodoListDto(todoListRepository.save(todoList));
    }

    public void deleteTodo(int userId, int todoId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));

        todoListRepository.deleteById(todoId);
    }

    public List<TodoListDto> getTodos(int userId) {
        return todoListRepository.findAllByOwnerId(userId).stream().map(TodoListMapper::toTodoListDto).collect(Collectors.toList());
    }

    public TodoListDto getTodo(int userId, int todoId) {
        return TodoListMapper.toTodoListDto(todoListRepository.findByOwnerIdAndId(userId, todoId));
    }
}
