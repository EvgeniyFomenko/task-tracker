package ru.efomenko.mapper;

import ru.efomenko.dto.NewUser;
import ru.efomenko.dto.RegistrationUserDto;
import ru.efomenko.dto.UserDto;
import ru.efomenko.entity.User;

public class UserMapper {
    public static User formNewUser(NewUser userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .build();
    }

    public static UserDto fromUser(User save) {
        return UserDto.builder()
                .id(save.getId())
                .email(save.getEmail())
                .blocked(save.isBlocked())
                .login(save.getLogin())
                .username(save.getUsername())
                .build();
    }

    public static User fromRegUser(RegistrationUserDto registrationUserDto) {
        return User.builder().email(registrationUserDto.getEmail()).login(registrationUserDto.getLogin()).password(registrationUserDto.getPassword()).build();
    }
}
