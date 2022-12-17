package ru.itmo.cloudtechonlogies.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.highloadsystems.model.AntistressToyType;
import ru.itmo.highloadsystems.service.AntistressToyTypeService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(value = "/antistress")
@RequiredArgsConstructor
public class AntistressToyProductionController {

    private final AntistressToyTypeService antistressToyTypeService;

    @GetMapping("")
    public ResponseEntity<List<AntistressToyType>> findAll(
            @RequestParam(defaultValue = "0") @Min(value = 0, message = "should be more 0") int page,
            @RequestParam(defaultValue = "5") @Max(value = 50, message = "should be less 50") int size
    ) {
        Page<AntistressToyType> antistressToyProductionPage = antistressToyTypeService.findAll(page, size);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("totalElements", String.valueOf(antistressToyProductionPage.getTotalElements()));
        responseHeaders.set("totalPage", String.valueOf(antistressToyProductionPage.getTotalPages()));
        return new ResponseEntity<>(antistressToyProductionPage.getContent(), responseHeaders, HttpStatus.OK);
    }
}
