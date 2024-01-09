package ru.efomenko.dto;

import lombok.Getter;


@Getter

public class NewUser extends UserDto {
    public NewUser(int id, String name, String login, boolean blocked, String email, String password) {
        super(id, name, login, blocked, email);
        this.password = password;
    }

    private String password;

    public void setPassword(String password) {
        this.password = password;
    }
}
