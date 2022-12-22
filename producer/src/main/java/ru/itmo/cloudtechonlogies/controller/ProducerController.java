package ru.itmo.cloudtechonlogies.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itmo.cloudtechonlogies.dto.TrackingDTO;
import ru.itmo.cloudtechonlogies.service.ProducerService;

import javax.jms.JMSException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/producer")
public class ProducerController {

    private final ProducerService producerService;

    @PutMapping("")
    public ResponseEntity<String> updateTracking(@RequestBody TrackingDTO trackingDTO) throws JMSException, JsonProcessingException {
        producerService.sendMsgToQueue(trackingDTO);
        return new ResponseEntity<>("Message successfully sent to queue! Well done!", HttpStatus.OK);
    }
}
