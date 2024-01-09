package ru.efomenko.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class FileDto {
    private int id;
    private String name;
    private String type;
    private Long size;
}
