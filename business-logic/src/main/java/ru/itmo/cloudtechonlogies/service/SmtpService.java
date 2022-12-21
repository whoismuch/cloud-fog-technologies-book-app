package ru.itmo.cloudtechonlogies.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.itmo.cloudtechonlogies.dto.BodyMessageDTO;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class SmtpService {
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    private final WebClient localApiClient;

    public String sendMessage(BodyMessageDTO bodyMessageDTO) {
        return localApiClient
                .post()
                .uri("http://localhost:8082/smtp/send")
                .bodyValue(BodyInserters.fromValue(bodyMessageDTO))
                .exchange()
                .flatMap(clientResponse -> {
                    if (clientResponse.statusCode().is5xxServerError()) {
                        clientResponse.body((clientHttpResponse, context) -> {
                            return clientHttpResponse.getBody();
                        });
                        return clientResponse.bodyToMono(String.class);
                    }
                    else
                        return clientResponse.bodyToMono(String.class);
                })
                .block();
    }

}
