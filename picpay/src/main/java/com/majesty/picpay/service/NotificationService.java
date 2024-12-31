package com.majesty.picpay.service;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.majesty.picpay.domain.user.User;
import com.majesty.picpay.dto.NotificationDTO;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        // Simula envio de notificação externo
        ResponseEntity<String> notificationResponse = restTemplate
                .postForEntity("https://util.devi.tools/api/v2/notify", notificationRequest, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.valueOf(Response.SC_OK))) {
            System.out.println("Status code: " + notificationResponse.getStatusCode());
            throw new Exception("Erro ao enviar notificação");
        }

    }

}
