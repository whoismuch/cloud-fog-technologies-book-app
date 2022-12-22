package ru.itmo.cloudtechonlogies.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.cloudtechonlogies.service.listener.TrackingMessageListenerService;

@Component
@RequiredArgsConstructor
public class Util implements Runnable {

    private final TrackingMessageListenerService trackingMessageListenerService;

    @Override
    public void run() {
        System.out.println("Util has been started");
        while (true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            trackingMessageListenerService.initConsumer();
        }
    }

}
