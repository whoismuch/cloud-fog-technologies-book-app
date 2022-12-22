package ru.itmo.cloudtechonlogies.service.listener;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.dto.TrackingDTO;
import ru.itmo.cloudtechonlogies.mapper.TrackingMapper;
import ru.itmo.cloudtechonlogies.service.TrackingService;

import javax.jms.*;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class TrackingMessageListenerService {

    private static String queueName = "msg-queue.fifo";
    private final TrackingService trackingService;
    private final TrackingMapper trackingMapper;

    public void initConsumer() {

        final AWSCredentials credentials = new BasicAWSCredentials("YCAJEXg4SSgXthMEtFTBLp2um", "YCOt2U9zbc6KUGOlW6sflv4I9GPMAtJB8LiAkCU-");

        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                AmazonSQSClientBuilder.standard()
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
            conn.start();
            Message message = consumer.receive();
            System.out.println(((TextMessage) message).getText());
            ObjectMapper mapper = new ObjectMapper();
            TrackingDTO trackingDTO = mapper.readValue(((TextMessage) message).getText(), TrackingDTO.class);
            trackingService.updateTracking(trackingMapper.mapDTOtoEntity(trackingDTO));
            Thread.sleep(1000);
        } catch (JMSException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
