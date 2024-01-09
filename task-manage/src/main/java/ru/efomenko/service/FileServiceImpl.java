package ru.efomenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.efomenko.dto.FileDto;
import ru.efomenko.entity.File;
import ru.efomenko.entity.Task;
import ru.efomenko.entity.User;
import ru.efomenko.exception.TaskNotFoundException;
import ru.efomenko.exception.UserNotFoundException;
import ru.efomenko.mapper.FileMapper;
import ru.efomenko.repository.FileRepository;
import ru.efomenko.repository.TaskRepository;
import ru.efomenko.repository.UserRepository;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Override
    public FileDto saveFile(MultipartFile file, int taskId, int userId) throws IOException {
        File fileSave = File.builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .type(file.getContentType()).build();
        if (file.isEmpty()) {
            throw new TaskNotFoundException("Failed to store empty file " + file.getName());
        }
        fileSave.setFile(file.getBytes());

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Таски не существует"));
        File fileInBase = fileRepository.save(fileSave);
        task.setFileId(fileInBase);
        taskRepository.save(task);

        return FileMapper.toFileDto(fileInBase);
    }

    @Override
    public Resource download(int idFile, int taskId, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Пользователя с таким id не существует"));
        Task task = taskRepository.findByIdAndTodoListOwnerId(taskId ,userId).orElseThrow(() -> new TaskNotFoundException(String.format("Таски с id = %d ", taskId)));
        File file = fileRepository.findById(idFile).orElseThrow(() -> new TaskNotFoundException("Таски не существует"));
        Resource resource = new ByteArrayResource(file.getFile(), file.getType() + " " + file.getName());

        return resource;
    }
}
