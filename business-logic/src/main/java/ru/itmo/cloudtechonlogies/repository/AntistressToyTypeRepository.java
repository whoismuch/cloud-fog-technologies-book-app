package ru.itmo.cloudtechonlogies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.highloadsystems.model.AntistressToyType;

import java.util.UUID;

public interface AntistressToyTypeRepository extends JpaRepository<AntistressToyType, UUID> {
}
