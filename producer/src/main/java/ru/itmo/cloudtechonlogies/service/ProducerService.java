package ru.itmo.cloudtechonlogies.service;

import com.amazon.sqs.javamessaging.AmazonSQSMessagingClientWrapper;
import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.dto.TrackingDTO;

import javax.jms.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private static String queueName = "msg-queue.fifo";

    public void sendMsgToQueue(TrackingDTO trackingDTO) throws JMSException {
        final AWSCredentials credentials = new BasicAWSCredentials("YCAJEXg4SSgXthMEtFTBLp2um", "YCOt2U9zbc6KUGOlW6sflv4I9GPMAtJB8LiAkCU-");

        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("ru-central1")
                .withEndpointConfiguration(new EndpointConfiguration(
                        "https://message-queue.api.cloud.yandex.net/",
                        "ru-central1"
                ))
                .build();

        SendMessageRequest sendMessageFifoQueue = new SendMessageRequest()
                .withQueueUrl("https://message-queue.api.cloud.yandex.net/b1g4mfhbnv017mt8dtdp/dj600000000apg9n02k5/msg-queue.fifo")
                .withMessageBody("Another simple message.")
                .withMessageGroupId("book-app")
                .withMessageBody("Vasiliska Super!");

        sqs.sendMessage(sendMessageFifoQueue);


//        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
//                new ProviderConfiguration(),
//                AmazonSQSClientBuilder.standard()
//                        .withRegion("ru-central1")
//                        .withEndpointConfiguration(new EndpointConfiguration(
//                                "https://message-queue.api.cloud.yandex.net/",
//                                "ru-central1"
//                        ))
//                        .withCredentials(credentialsProvider)
//        );
//
//        SQSConnection connection = connectionFactory.createConnection();
//
//        AmazonSQSMessagingClientWrapper client = connection.getWrappedAmazonSQSClient();
//
//        System.out.println(client.getQueueUrl("msg-queue.fifo"));
//
//        if (!client.queueExists(queueName)) {
//            client.createQueue(queueName);
//        }
//
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//        Queue queue = session.createQueue(queueName);
//
//        SendMessageRequest
//
//        MessageProducer producer = session.createProducer(queue);
//
//        Message message = session.createTextMessage("Vasiliska Super!");
//        message.setStringProperty("MessageGroupId", "1c647vcgdh");
//        producer.send(message);

//        MessageConsumer consumer = session.createConsumer(queue);
//        connection.start();
//        message = consumer.receive(1000);
//        System.out.println(((TextMessage) message).getText());
    }

}
