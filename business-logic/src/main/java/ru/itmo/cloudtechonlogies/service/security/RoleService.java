package ru.itmo.cloudtechonlogies.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.repository.RoleRepository;
import ru.itmo.cloudtechonlogies.exception.NotFoundElementException;
import ru.itmo.cloudtechonlogies.model.Role;
import ru.itmo.cloudtechonlogies.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundElementException("Not found role"));
    }
}
