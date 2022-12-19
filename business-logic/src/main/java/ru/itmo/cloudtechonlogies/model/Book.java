package ru.itmo.cloudtechonlogies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book", schema = "public")
public class Book {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "author")
    private String author;

    @NotBlank
    @Column(name = "audio_path")
    private String audioPath;

    @NotBlank
    @Column(name = "text_path")
    private String textPath;

    @NotNull
    @Min(value = 1)
    @Column(name = "page_count")
    private Integer pageCount;

    @NotNull
    @Min(value = 1)
    @Column(name = "audio_length")
    private Integer audioLength;

}
