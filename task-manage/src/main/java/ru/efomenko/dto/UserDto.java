package ru.efomenko.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserDto {
    private int id;
    private String username;
    private String login;
    private boolean blocked;
    private String email;
}
