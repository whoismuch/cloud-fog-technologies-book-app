package ru.itmo.cloudtechonlogies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Size(min = 5, max = 50)
    @Column(unique = true)
    private String login;

    @NotBlank
    @Size(min = 8, max = 128)
    @Column(name = "password")
    private String password;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Size(min = 10, max = 12)
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @Past
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;
}
