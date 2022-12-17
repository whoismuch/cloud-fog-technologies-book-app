package ru.itmo.cloudtechonlogies.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryBookDTO {

    private UUID id;
    private String name;
    private String author;
    private String audioPath;
    private String textPath;
    private String categoryName;

}
