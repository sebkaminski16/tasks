package com.crud.tasks.service;

import com.crud.tasks.trello.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTestSuite{

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleMailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @InjectMocks
    private TrelloService trelloService;

    private TrelloCardDto trelloCardDto;
    private CreatedTrelloCardDto createdCardDto;

    @BeforeEach
    void setUp() {
        trelloCardDto = new TrelloCardDto("Test Card", "Description", "top", "1");
        createdCardDto = new CreatedTrelloCardDto("123", "Test Card", "http://test.com");
    }

    @Test
    void testFetchTrelloBoards() {
        //arrange
        List<TrelloBoardDto> trelloBoards = List.of(new TrelloBoardDto("1", "Board A", List.of()));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);
        //act
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();
        //asert
        assertEquals(1, result.size());
        assertEquals("Board A", result.get(0).getName());
        verify(trelloClient, times(1)).getTrelloBoards();
    }

    @Test
    void testCreateTrelloCardShouldSendEmailWhenCardCreated() {
        //arrange
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdCardDto);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");
        //act
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);
        //assert
        assertNotNull(result);
        assertEquals("123", result.getId());
        assertEquals("Test Card", result.getName());
        verify(trelloClient, times(1)).createNewCard(trelloCardDto);
    }

    @Test
    void testCreateTrelloCardShouldNotSendEmailWhenCardIsNull() {
        //arrange
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(null);
        //act
        CreatedTrelloCardDto result = trelloService.createTrelloCard(trelloCardDto);
        //assert
        assertNull(result);
        verify(trelloClient, times(1)).createNewCard(trelloCardDto);
        verify(emailService, never()).send(any(Mail.class));
    }
}
