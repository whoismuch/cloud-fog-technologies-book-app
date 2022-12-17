package ru.itmo.cloudtechonlogies.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class UserDTORequest {
    @NotBlank(message = "cannot be null")
    @Size(min = 5, max = 50, message = "must be between 5 and 50 characters")
    private String login;

    @NotBlank(message = "cannot be null")
    @Size(min = 8, max = 128, message = "must be between 8 and 128 characters")
    private String password;

    @NotBlank(message = "cannot be null")
    private String role;

    @NotBlank(message = "cannot be null")
    @Size(min = 4, max = 50, message = "must be between 4 and 50 characters")
    private String name;

    @NotBlank(message = "cannot be null")
    @Size(min = 4, max = 50, message = "must be between 4 and 50 characters")
    private String surname;

    @NotBlank(message = "cannot be null")
    @Size(min = 8, max = 320, message = "must be between 8 and 320 characters")
    private String email;

    @NotBlank(message = "cannot be null, empty")
    private Date dateOfBirth;
}
