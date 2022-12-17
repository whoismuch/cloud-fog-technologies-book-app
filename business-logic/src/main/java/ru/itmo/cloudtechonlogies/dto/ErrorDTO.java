package ru.itmo.cloudtechonlogies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDTO implements Serializable {
    String errorMessage;
}
