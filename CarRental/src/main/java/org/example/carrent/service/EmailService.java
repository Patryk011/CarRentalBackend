package org.example.carrent.service;

public interface EmailService {
    void sendEmail(String toEmail, String subject, String body);
}
