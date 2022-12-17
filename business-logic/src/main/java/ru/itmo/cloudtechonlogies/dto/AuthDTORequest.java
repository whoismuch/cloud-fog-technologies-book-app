package ru.itmo.cloudtechonlogies.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthDTORequest {
    @NotBlank(message = "cannot be null")
    @Size(min = 5, max = 50, message = "must be between 5 and 50 characters")
    private String login;

    @NotBlank(message = "cannot be null")
    @Size(min = 8, max = 128, message = "must be between 8 and 128 characters")
    private String password;
}
