package ru.efomenko.mapper;

import ru.efomenko.dto.FileDto;
import ru.efomenko.entity.File;

public class FileMapper {
    public static FileDto toFileDto(File save) {
        return FileDto.builder().id(save.getId()).type(save.getType()).name(save.getName()).size(save.getSize()).build();
    }
}
