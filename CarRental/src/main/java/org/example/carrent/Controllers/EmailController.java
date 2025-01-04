package org.example.carrent.Controllers;

import org.example.carrent.dto.EmailDTO;
import org.example.carrent.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDTO email) {

        emailService.sendEmail(email.getToEmail(), email.getSubject(), email.getBody());
        return ResponseEntity.ok("Email sent successfully!");
    }

}
