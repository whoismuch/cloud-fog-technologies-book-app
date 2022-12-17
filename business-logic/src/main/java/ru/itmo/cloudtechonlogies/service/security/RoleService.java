package ru.itmo.cloudtechonlogies.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.highloadsystems.exception.NotFoundElementException;
import ru.itmo.highloadsystems.model.Role;
import ru.itmo.highloadsystems.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getRole(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundElementException("Not found role"));
    }
}
