package ru.itmo.cloudtechonlogies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "antistress_toy_production", schema = "public")
public class AntistressToyProduction {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "antistress_toy_type_id", referencedColumnName = "id")
    private AntistressToyType antistressToyType;

    @NotNull
    @Column(name = "is_available")
    private boolean isAvailable;
}
