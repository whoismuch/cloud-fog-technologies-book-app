package ru.itmo.cloudtechonlogies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.highloadsystems.model.AntistressToyProduction;
import ru.itmo.highloadsystems.model.AntistressToyType;

import java.util.Optional;
import java.util.UUID;

public interface AntistressToyProductionRepository extends JpaRepository<AntistressToyProduction, UUID> {
    Optional<AntistressToyProduction> findFirstByAntistressToyTypeAndIsAvailable(AntistressToyType antistressToyType, Boolean isAvailable);

    @Modifying
    @Query("update AntistressToyProduction atp set atp.isAvailable = false where atp.id = :id")
    void makeToyNotAvailableById(UUID id);


}
