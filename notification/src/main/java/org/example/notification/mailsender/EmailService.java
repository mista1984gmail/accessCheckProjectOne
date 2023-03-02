package org.example.notification.mailsender;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    public void sendEmail(String email, String messageForCustomer){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("testmyservice80@gmail.com");
        message.setTo(email);
        message.setSubject("Welcome to Service!!!");
        message.setText(messageForCustomer);
        javaMailSender.send(message);
    }
}
