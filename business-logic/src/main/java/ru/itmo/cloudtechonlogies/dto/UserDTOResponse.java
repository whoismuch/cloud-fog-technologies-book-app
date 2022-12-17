package ru.itmo.cloudtechonlogies.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTOResponse {
    private UUID id;
    private String login;
    private String name;
    private String surname;
    private String email;
    private Date dateOfBirth;
}
