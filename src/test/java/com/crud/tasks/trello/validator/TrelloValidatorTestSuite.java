package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloValidatorTestSuite {

    private final TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    void testValidateCardWhenCardNameContainsTest() {
        //arrange
        TrelloCard trelloCard = new TrelloCard("Test card", "description", "top", "1");
        //act&assert
        trelloValidator.validateCard(trelloCard);
    }

    @Test
    void testValidateCardWhenCardNameDoesNotContainTest() {
        //arrange
        TrelloCard trelloCard = new TrelloCard("Test card", "description", "top", "1");
        //act &assert
        assertDoesNotThrow(() -> trelloValidator.validateCard(trelloCard));
    }

    @Test
    void testValidateTrelloBoardsShouldFilterOutTestBoards() {
        //arrange
        List<TrelloBoard> boards = List.of(
                new TrelloBoard("1", "Test Board", List.of()),
                new TrelloBoard("2", "Test", List.of()),
                new TrelloBoard("3", "test", List.of())
        );
        //act
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(boards);
        //assert
        assertEquals(1, filteredBoards.size());
        assertEquals("Test Board", filteredBoards.get(0).getName());
    }

    @Test
    void testValidateTrelloBoardsWhenNoBoardsFilteredOut() {
        //arrangen
        List<TrelloBoard> boards = List.of(
                new TrelloBoard("1", "Board A", List.of()),
                new TrelloBoard("2", "Board B", List.of())
        );
        //act
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(boards);
        //assert
        assertEquals(2, filteredBoards.size());
        assertTrue(filteredBoards.stream().allMatch(board -> !board.getName().equalsIgnoreCase("test")));
    }
}
