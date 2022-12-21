package ru.itmo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/smtp")
public class SmtpController {
    private final Sender sender;

    @PostMapping ("/send")
    public ResponseEntity<String> sendMail(@RequestBody BodyMess body) {
        sender.send(body.getReceiver(), body.getSubject(), body.getMessage());
      return new ResponseEntity<>("Mail send", HttpStatus.OK);
    }

}
