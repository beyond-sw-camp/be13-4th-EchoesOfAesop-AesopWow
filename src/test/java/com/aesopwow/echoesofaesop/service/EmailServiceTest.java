package com.aesopwow.echoesofaesop.service;

import com.aesopwow.echoesofaesop.common.email.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @Test
    void sendEmail() {
        Map<String, Object> parameters =  new HashMap<>();
        parameters.put("otp", "123456");
        emailService.sendMimeMail("g0xdbyul@gmail.com", "Test Email", parameters, "otp-template");
    }
}
