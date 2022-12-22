package ru.itmo.cloudtechonlogies.service.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.classic.methods.HttpPost;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.client5.http.impl.classic.HttpClients;
import com.github.dockerjava.zerodep.shaded.org.apache.hc.core5.http.io.entity.StringEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itmo.cloudtechonlogies.dto.EmailMessageDTO;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class SmtpClient {

    public CloseableHttpResponse callAPISmtpService(String url, EmailMessageDTO bodyMessageDTO) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException, IOException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();

        ObjectMapper mapper = new ObjectMapper();
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(new StringEntity(mapper.writeValueAsString(bodyMessageDTO)));

        return client.execute(httpPost);
    }

}
