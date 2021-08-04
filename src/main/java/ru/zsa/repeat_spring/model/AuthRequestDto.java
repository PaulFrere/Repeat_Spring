package ru.zsa.repeat_spring.model;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String login;
    private String password;
}
