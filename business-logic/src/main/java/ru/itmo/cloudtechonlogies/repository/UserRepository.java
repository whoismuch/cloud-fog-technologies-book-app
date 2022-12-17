package ru.itmo.cloudtechonlogies.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.highloadsystems.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLogin(String login);
}