package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.highloadsystems.dto.UserDTORequest;
import ru.itmo.highloadsystems.exception.ExistElementException;
import ru.itmo.highloadsystems.exception.NoMatchException;
import ru.itmo.highloadsystems.exception.NotFoundElementException;
import ru.itmo.highloadsystems.model.User;
import ru.itmo.highloadsystems.repository.UserRepository;
import ru.itmo.highloadsystems.service.security.RoleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Secured({"ROLE_USER", "ROLE_DELIVERY"})
    public User findByLogin(String login) {
        return userRepository.findByLogin(login).orElseThrow(() -> new NoMatchException("Not found user with this login"));
    }

    public User saveUser(UserDTORequest userDTORequest) {
        if (userRepository.findByLogin(userDTORequest.getLogin()).isPresent()) {
            throw new ExistElementException("This user has already exist");
        }
        User u = new User();
        u.setLogin(userDTORequest.getLogin());
        u.setDateOfBirth(userDTORequest.getDateOfBirth());
        u.setPhoneNumber(userDTORequest.getPhoneNumber());
        u.setSurname(userDTORequest.getSurname());
        u.setName(userDTORequest.getName());
        u.setRole(roleService.getRole(userDTORequest.getRole()));
        u.setPassword(passwordEncoder.encode(userDTORequest.getPassword()));
        return userRepository.save(u);
    }

    public User findByLoginAndPassword(String login, String password) throws NotFoundElementException {
        User userEntity = userRepository.findByLogin(login).orElseThrow(() -> new NotFoundElementException("User with this login not found"));
        if (passwordEncoder.matches(password, userEntity.getPassword())) {
            return userEntity;
        } else {
            throw new NoMatchException("Not right password");
        }
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public Page<User> findAll(int page, int size){
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public User findById(UUID id) {
       return userRepository.findById(id).orElseThrow(() -> new NotFoundElementException("User not found"));
    }
}