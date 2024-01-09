package ru.efomenko.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import ru.efomenko.dto.FileDto;

import java.io.IOException;

public interface FileService {
    FileDto saveFile(MultipartFile file, int taskId, int userId) throws IOException;

    Resource download(int idFile, int taskId, int userId);
}
