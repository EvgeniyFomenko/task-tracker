package ru.efomenko.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.efomenko.entity.Task;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class TodoListDto {
    private int id;
    private String name;
    private List<Task> task;
}
