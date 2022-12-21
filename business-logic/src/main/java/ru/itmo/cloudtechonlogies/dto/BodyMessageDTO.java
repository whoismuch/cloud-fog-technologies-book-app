package ru.itmo.cloudtechonlogies.dto;

import lombok.Data;

@Data
public class BodyMessageDTO {
    private String message;
    private String receiver;
    private String subject;
}
