package ru.efomenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Service;
import ru.efomenko.dto.TaskDto;
import ru.efomenko.entity.Task;
import ru.efomenko.entity.TodoList;
import ru.efomenko.exception.TaskNotFoundException;
import ru.efomenko.exception.TaskValidFailException;
import ru.efomenko.exception.TodoEcxeption;
import ru.efomenko.mapper.TaskMapper;
import ru.efomenko.repository.TaskRepository;
import ru.efomenko.repository.TodoListRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TodoListRepository todoListRepository;

    @Override
    public TaskDto postTask(TaskDto taskDto, int todoId) {
        TodoList todoList = todoListRepository.findById(todoId).orElseThrow(() -> new TodoEcxeption("Not found todo"));
        Task task = TaskMapper.fromDto(taskDto);
        task.setTodoList(todoList);
        return TaskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskDto getTaskById(int id) {
        return TaskMapper.toDto(taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(String.format("Task with id %d not found", id))));
    }

    @Override
    public List<TaskDto> getTasks(int userId, int todolist) {
        TodoList todoLists = todoListRepository.findById(todolist).orElseThrow(() -> new TodoEcxeption("Todo list not find"));
        List<Task> taskList;
        taskList = todoLists.getTasks();
        return taskList.stream().map(TaskMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public TaskDto pathTask(TaskDto taskDto, int todoId) {
        validate(taskDto);
        Task task = taskRepository.findById(taskDto.getId()).orElseThrow(() -> new TaskNotFoundException(String.format("Task with id %d not found", taskDto.getId())));

        return TaskMapper.toDto(taskRepository.save(task));
    }

    @Override
    public void deleteTaskByUserIdAndTaskId(int userId, int taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(String.format("Task with id %d not found", taskId)));
        TodoList todoList = task.getTodoList();

        if (todoList.getOwner().getId() != userId) {
            throw new TaskRejectedException("User dont can to delete this task, access is denied");
        }

        taskRepository.delete(task);

    }

    private void validate(TaskDto task) {
        if (task.getName().isEmpty() || task.getName().isBlank()) {
            throw new TaskValidFailException("Task name cannot be with blank or empty");
        }
    }
}
