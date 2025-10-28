package com.crud.tasks.scheduler;

import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleMailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTestSuite {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleMailService simpleMailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldSendInformationEmail() {
        //arrange
        when(taskRepository.count()).thenReturn(5L);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");

        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);
        //act
        emailScheduler.sendInformationEmail();
        //assert
        verify(simpleMailService, times(1)).send(mailCaptor.capture());
        Mail sentMail = mailCaptor.getValue();

        assertEquals("admin@test.com", sentMail.getMailTo());
        assertEquals("Tasks: Once a day email", sentMail.getSubject());
        assertEquals("Currently in database you got: 5tasks", sentMail.getMessage());
    }
}
