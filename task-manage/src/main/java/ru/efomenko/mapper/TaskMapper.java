package ru.efomenko.mapper;

import ru.efomenko.dto.TaskDto;
import ru.efomenko.entity.Task;


public class TaskMapper {
    public static Task fromDto(TaskDto taskDto) {
        return Task.builder().description(taskDto.getDescription())
                .name(taskDto.getName())
                .id(taskDto.getId())
                .fileId(taskDto.getFileId())
                .build();
    }

    public static TaskDto toDto(Task task) {
        return TaskDto.builder().id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .fileId(task.getFileId())
                .build();
    }
}
