package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MailConfig {
    @Value("${info.company.name}")
    private String companyName;
    @Value("${email.goodbye_message}")
    private String goodbyeMessage;
}
