package ru.efomenko.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.efomenko.dto.FileDto;
import ru.efomenko.dto.TaskDto;
import ru.efomenko.service.FileService;
import ru.efomenko.service.TaskService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final FileService fileService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/todolist/{todoId}/task")
    public TaskDto postTask(@RequestBody TaskDto taskDto, @PathVariable int todoId) {
        return taskService.postTask(taskDto, todoId);
    }

    @GetMapping("/task/{id}")
    public TaskDto getTask(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/user/{userId}/todolist/{todoId}/tasks")
    public List<TaskDto> getTasks(@PathVariable int userId, @PathVariable int todoId) {
        return taskService.getTasks(userId, todoId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{userId}/task/{taskId}")
    public void deleteTask(@PathVariable int userId, @PathVariable int taskId) {
        taskService.deleteTaskByUserIdAndTaskId(userId, taskId);
    }

    @PatchMapping("/user/{userId}/todolist/{todoId}/task/{taskId}")
    public TaskDto patchTask(@PathVariable int id, @RequestBody TaskDto task, @PathVariable int todoId) {
        task.setId(id);
        return taskService.pathTask(task, todoId);
    }

    @PostMapping("/user/{userId}/todolist/{todoId}/task/{taskId}/file")
    public FileDto postFile(@RequestParam MultipartFile file, @PathVariable int taskId, @PathVariable int userId) throws IOException {
        return fileService.saveFile(file, taskId, userId);
    }

    @GetMapping("/user/{userId}/todolist/{todoId}/task/{taskId}/file/{fileId}")
    public ResponseEntity<Resource> dwnloadFile(@PathVariable int taskId, @PathVariable int userId, @PathVariable int fileId) {
        Resource resource = fileService.download(fileId,taskId,userId);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getDescription() + "\"").body(resource);
    }
}
