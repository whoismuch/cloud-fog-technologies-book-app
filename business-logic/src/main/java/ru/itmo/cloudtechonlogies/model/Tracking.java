package ru.itmo.cloudtechonlogies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itmo.cloudtechonlogies.model.key.TrackingId;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tracking", schema = "public")
@IdClass(TrackingId.class)
public class Tracking {

    @Id
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;

    @Id
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_book", referencedColumnName = "id")
    private Book book;

    @NotNull
    @Min(value = 0)
    @Column(name = "page")
    private Integer page;

    @NotNull
    @Min(value = 0)
    @Column(name = "timer")
    private Integer timer;


}
