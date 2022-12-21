package ru.itmo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/smtp")
public class SmtpController {
    private static final Sender sender = new Sender("v.lisitsina@gmail.com", "ykabadueqlizbssj");


    @PostMapping ("/send")
    public ResponseEntity<String> sendMail(@RequestBody BodyMess body, @RequestParam String receiver) {
        sender.send(receiver, "aa", body.getMessage());
      return new ResponseEntity<>("Mail send", HttpStatus.OK);
    }

}
