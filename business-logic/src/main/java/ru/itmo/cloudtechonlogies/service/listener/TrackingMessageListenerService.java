package ru.itmo.cloudtechonlogies.service.listener;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.listener.TrackingMessageListener;

import javax.jms.*;

@Service
@RequiredArgsConstructor
public class TrackingMessageListenerService {

    private static String queueName = "msg-queue.fifo";


    public void initConsumer() {

        final AWSCredentials credentials = new BasicAWSCredentials("YCAJEXg4SSgXthMEtFTBLp2um", "YCOt2U9zbc6KUGOlW6sflv4I9GPMAtJB8LiAkCU-");

        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClientBuilder.standard()
                        .withRegion("ru-central1")
                        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                                "https://message-queue.api.cloud.yandex.net/",
                                "ru-central1"
                        ))
                        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        );

        try (SQSConnection conn = connectionFactory.createConnection()) {
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);
            MessageConsumer consumer = session.createConsumer(queue);
            consumer.setMessageListener(new TrackingMessageListener());
            conn.start();
//            Thread.sleep(1000000);
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }

}
