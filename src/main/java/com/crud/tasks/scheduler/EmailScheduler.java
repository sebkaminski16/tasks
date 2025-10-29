package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleMailService;
import com.crud.tasks.trello.config.AdminConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleMailService simpleMailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "30 52 16 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleMailService.send(
                Mail.builder()
                        .mailTo(adminConfig.getAdminMail())
                        .subject(SUBJECT)
                        .message("Currently in database you got: " + size + (size > 1 ? "tasks" : "task"))
                        .build(),
                SimpleMailService.KindOfMail.TASK_NUMBER_INFORMATION
        );
    }
}