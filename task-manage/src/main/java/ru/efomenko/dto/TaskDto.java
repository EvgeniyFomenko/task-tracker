package ru.efomenko.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.efomenko.entity.File;

@Getter
@Setter
@ToString
@Builder
public class TaskDto {
    private int id;
    private String name;
    private String description;
    private File fileId;
}
