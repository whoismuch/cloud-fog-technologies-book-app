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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.dto.TrackingDTO;

import javax.jms.*;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private static String queueName = "msg-queue.fifo";

    private static int i = 0;
    public void sendMsgToQueue(TrackingDTO trackingDTO) throws JMSException, JsonProcessingException {
        final AWSCredentials credentials = new BasicAWSCredentials("YCAJEXg4SSgXthMEtFTBLp2um", "YCOt2U9zbc6KUGOlW6sflv4I9GPMAtJB8LiAkCU-");

        i ++;
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("ru-central1")
                .withEndpointConfiguration(new EndpointConfiguration(
                        "https://message-queue.api.cloud.yandex.net/",
                        "ru-central1"
                ))
                .build();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(trackingDTO);

        SendMessageRequest sendMessageFifoQueue = new SendMessageRequest()
                .withQueueUrl("https://message-queue.api.cloud.yandex.net/b1g4mfhbnv017mt8dtdp/dj600000000apg9n02k5/msg-queue.fifo")
                .withMessageBody(json)
                .withMessageGroupId("book-app");

        sqs.sendMessage(sendMessageFifoQueue);
    }

}
