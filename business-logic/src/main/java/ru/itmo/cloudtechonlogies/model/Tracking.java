package ru.itmo.cloudtechonlogies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tracking", schema = "public")
public class Tracking {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_book", referencedColumnName = "id")
    private Book book;

    @NotNull
    @Min(value = 1)
    @Column(name = "page")
    private Integer page;

    @NotNull
    @Min(value = 1)
    @Column(name = "timer")
    private Long timer;


}
