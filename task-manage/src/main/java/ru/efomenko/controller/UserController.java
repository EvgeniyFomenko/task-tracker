package ru.efomenko.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.efomenko.dto.UserDto;
import ru.efomenko.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @DeleteMapping("/admin/delete/user/{userId}")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/user/{userId}")
    public UserDto getUser(@PathVariable int userId) {
        return userService.findUserById(userId);
    }

    @GetMapping("/admin/users")
    public List<UserDto> getUser() {
        return userService.findUsers();
    }

}
