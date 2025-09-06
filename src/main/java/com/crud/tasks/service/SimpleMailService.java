package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMailService {

    private final JavaMailSender javaMailSender;

    private SimpleMailMessage createMailMessage(Mail mail) {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo(mail.getMailTo());
        smm.setSubject(mail.getSubject());
        smm.setText(mail.getMessage());
        Optional<String> mailCc = Optional.ofNullable(mail.getCcTo());
        mailCc.ifPresent(smm::setCc);
        return smm;
    }

    public void send(Mail mail) {
        log.info("Starting email preparation...");

        try {
            SimpleMailMessage smm = createMailMessage(mail);
            javaMailSender.send(smm);
            log.info("Email has been sent!");
        } catch (MailException e) {
            log.error("Failed to send an email: " + e.getMessage(), e);
        }
    }

}
