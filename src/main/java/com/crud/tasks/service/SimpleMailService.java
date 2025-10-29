package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleMailService {

    public enum KindOfMail {
        TRELLO_CARD,
        TASK_NUMBER_INFORMATION
    }

    @Autowired
    private MailCreatorService mailCreatorService;
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

    private MimeMessagePreparator createMimeMessage(final Mail mail, KindOfMail kindOfMail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            switch(kindOfMail) {
                case KindOfMail.TRELLO_CARD -> messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
                case KindOfMail.TASK_NUMBER_INFORMATION -> messageHelper.setText(mailCreatorService.buildTaskNumberInformationEmail(mail.getMessage()), true);
            }
        };
    }

    public void send(Mail mail, KindOfMail kindOfMail) {
        log.info("Starting email preparation...");

        try {
            javaMailSender.send(createMimeMessage(mail, kindOfMail));
            log.info("Email has been sent!");
        } catch (MailException e) {
            log.error("Failed to send an email: " + e.getMessage(), e);
        }
    }

}
