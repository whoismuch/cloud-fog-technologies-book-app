package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.highloadsystems.model.AntistressToyProduction;
import ru.itmo.highloadsystems.model.AntistressToyType;
import ru.itmo.highloadsystems.repository.AntistressToyProductionRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AntistressToyProductionService {

    private final AntistressToyProductionRepository antistressToyProductionRepository;

    public void takeToys (AntistressToyType antistressToyType, int count) throws NoSuchElementException {
        for (int i=0; i<count; i++) {
            Optional<AntistressToyProduction> antistressToyProduction = antistressToyProductionRepository
                    .findFirstByAntistressToyTypeAndIsAvailable(antistressToyType, true);
            if (antistressToyProduction.isEmpty()) throw new NoSuchElementException("There is no available toy of this type");
            antistressToyProductionRepository.makeToyNotAvailableById(antistressToyProduction.get().getId());
        }
    }


}
