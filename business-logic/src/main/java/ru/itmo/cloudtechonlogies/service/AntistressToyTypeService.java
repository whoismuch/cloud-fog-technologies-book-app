package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itmo.highloadsystems.model.AntistressToyType;
import ru.itmo.highloadsystems.repository.AntistressToyTypeRepository;

@Service
@RequiredArgsConstructor
public class AntistressToyTypeService {

    private final AntistressToyTypeRepository antistressToyTypeRepository;

    public Page<AntistressToyType> findAll(int page, int size){
        return (antistressToyTypeRepository.findAll(PageRequest.of(page, size)));
    }
}
