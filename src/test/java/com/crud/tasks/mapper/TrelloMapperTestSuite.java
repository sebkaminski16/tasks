package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTestSuite {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void testMapToBoards() {
        //arrange
        List<TrelloListDto> listDtos = List.of(new TrelloListDto("1", "Test List", false));
        List<TrelloBoardDto> boardDtos = List.of(new TrelloBoardDto("1", "Test Board", listDtos));
        //act
        List<TrelloBoard> result = trelloMapper.mapToBoards(boardDtos);
        TrelloBoard board = result.getFirst();
        //assert
        assertEquals(1, result.size());
        assertEquals("1", board.getId());
        assertEquals("Test Board", board.getName());
        assertEquals(1, board.getLists().size());
        assertEquals("Test List", board.getLists().getFirst().getName());
    }

    @Test
    void testMapToBoardsDto() {
        //arrange
        List<TrelloList> lists = List.of(new TrelloList("1", "List A", false));
        List<TrelloBoard> boards = List.of(new TrelloBoard("1", "Board A", lists));
        //act
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(boards);
        TrelloBoardDto boardDto = result.getFirst();
        //assert
        assertEquals(1, result.size());
        assertEquals("1", boardDto.getId());
        assertEquals("Board A", boardDto.getName());
        assertEquals(1, boardDto.getLists().size());
        assertEquals("List A", boardDto.getLists().getFirst().getName());
    }

    @Test
    void testMapToList() {
        //arrange
        List<TrelloListDto> listDtos = List.of(
                new TrelloListDto("1", "ListDto1", false),
                new TrelloListDto("2", "ListDto2", true)
        );
        //act
        List<TrelloList> result = trelloMapper.mapToList(listDtos);
        //assert
        assertEquals(2, result.size());
        assertEquals("1", result.getFirst().getId());
        assertFalse(result.getFirst().isClosed());
        assertTrue(result.get(1).isClosed());
    }

    @Test
    void testMapToListDto() {
        //arrange
        List<TrelloList> lists = List.of(
                new TrelloList("1", "List1", false),
                new TrelloList("2", "List2", true)
        );
        //act
        List<TrelloListDto> result = trelloMapper.mapToListDto(lists);
        //assert
        assertEquals(2, result.size());
        assertEquals("1", result.getFirst().getId());
        assertEquals("List2", result.get(1).getName());
        assertTrue(result.get(1).isClosed());
    }

    @Test
    void testMapToCardDto() {
        //arrange
        TrelloCard card = new TrelloCard("card", "description", "top", "1");
        //act
        TrelloCardDto result = trelloMapper.mapToCardDto(card);
        //assert
        assertEquals("card", result.getName());
        assertEquals("description", result.getDescription());
        assertEquals("top", result.getPos());
        assertEquals("1", result.getListId());
    }

    @Test
    void testMapToCard() {
        //arrange
        TrelloCardDto cardDto = new TrelloCardDto("card", "description", "bottom", "1");
        //act
        TrelloCard result = trelloMapper.mapToCard(cardDto);
        //assert
        assertEquals("card", result.getName());
        assertEquals("description", result.getDescription());
        assertEquals("bottom", result.getPos());
        assertEquals("1", result.getListId());
    }
}
