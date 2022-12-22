package ru.itmo.cloudtechonlogies.listener;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.service.listener.TrackingMessageListenerService;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class TrackingMessageListener {
    private final Util util;

    public TrackingMessageListener(Util util) {
        this.util = util;
        onMessage();
    }

    public void onMessage() {
        util.run();
    }
}
