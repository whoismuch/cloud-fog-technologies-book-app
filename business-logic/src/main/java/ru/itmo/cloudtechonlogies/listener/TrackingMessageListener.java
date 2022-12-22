package ru.itmo.cloudtechonlogies.listener;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class TrackingMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(message.getBody(String.class));
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
