package com.crud.tasks.domain;

import lombok.*;

// used this: https://projectlombok.org/features/Builder

@Getter
@Builder
public class Mail {
    private String ccTo;
    private String mailTo;
    private String subject;
    private String message;
}
