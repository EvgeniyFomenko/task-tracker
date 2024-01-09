package ru.efomenko.service;

import ru.efomenko.dto.TaskDto;

import java.util.List;


public interface TaskService {

    TaskDto postTask(TaskDto taskDto, int todoId);

    TaskDto getTaskById(int id);

    List<TaskDto> getTasks(int userId, int todoList);

    TaskDto pathTask(TaskDto task, int todoId);

    void deleteTaskByUserIdAndTaskId(int userId, int taskId);
}
