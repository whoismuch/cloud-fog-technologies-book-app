package ru.itmo.cloudtechonlogies.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "antistress_toy_type_to_stress_type", schema = "public")
public class AntistressToyTypeToStressType {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "antistress_toy_type_id", referencedColumnName = "id")
    private AntistressToyType antistressToyType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "stress_type_id", referencedColumnName = "id")
    private StressType stressType;

    @NotNull
    @Min(value = 0)
    @Max(value = 10)
    @Column(name = "efficiency")
    private int efficiency;
}
