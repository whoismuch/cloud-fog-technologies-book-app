package ru.itmo.cloudtechonlogies.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.classic.methods.HttpPost;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.HttpClients;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.dto.EmailMessageDTO;
import ru.itmo.cloudtechonlogies.service.client.AmazonClient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Service
public class ObjectStorageService {

    public byte[] getFile(String bucketName, String fileName) throws IOException {
        AmazonS3 s3Client = AmazonClient.getClient();

        GetObjectRequest request = new GetObjectRequest(bucketName, fileName);

        S3Object o = s3Client.getObject(request);
        S3ObjectInputStream s3is = o.getObjectContent();
        byte[] content = IOUtils.toByteArray(s3is);
        s3is.close();
        return content;

    }
}
