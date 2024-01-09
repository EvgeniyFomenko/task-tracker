package ru.efomenko.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.efomenko.dto.RegistrationUserDto;
import ru.efomenko.dto.UserDto;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    UserDto findUser(String login);


    UserDto createUser(RegistrationUserDto userDto);

    void deleteUser(int userId);

    UserDto findUserById(int userId);

    List<UserDto> findUsers();
}
