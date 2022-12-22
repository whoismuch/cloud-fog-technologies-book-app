package ru.itmo.cloudtechonlogies.service.client;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AmazonClient {

    private static final String accessStaticKeyId = "YCAJEXg4SSgXthMEtFTBLp2um";
    private static final String accessStaticKey = "YCOt2U9zbc6KUGOlW6sflv4I9GPMAtJB8LiAkCU-";

    public static AmazonS3 getClient() {
        AWSCredentials credentials = new BasicAWSCredentials(accessStaticKeyId, accessStaticKey);
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(
                                "storage.yandexcloud.net", "ru-central1"
                        )
                )
                .build();
    }
}
