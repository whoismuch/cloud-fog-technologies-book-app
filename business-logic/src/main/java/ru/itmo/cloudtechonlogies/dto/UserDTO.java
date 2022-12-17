package ru.itmo.cloudtechonlogies.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private String login;
    private String password;
    private String role;
    private String name;
    private String surname;
    private String email;
    private Date dateOfBirth;
}
