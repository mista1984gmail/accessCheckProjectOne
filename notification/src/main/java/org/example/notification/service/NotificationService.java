package org.example.notification.service;

import lombok.AllArgsConstructor;
import org.example.clients.notification.NotificationRequest;
import org.example.notification.mailsender.EmailService;
import org.example.notification.model.Notification;
import org.example.notification.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public void send(NotificationRequest notificationRequest) {
        Notification notification = Notification.builder()
                .toCustomerId(notificationRequest.getToCustomerId())
                .toCustomerEmail(notificationRequest.getToCustomerEmail())
                .sender("Service")
                .message(notificationRequest.getMessage())
                .sentAt(LocalDateTime.now())
                .build();
        notificationRepository.save(notification);
        emailService.sendEmail(notification.getToCustomerEmail(), notification.getMessage());
    }
}
