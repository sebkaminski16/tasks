package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public void getTrelloBoards() {
        List<TrelloBoardDto> boards = trelloClient.getTrelloBoards();
        boards
                .stream()
                .filter(trelloBoardDto -> !trelloBoardDto.getName().isEmpty()
                        && !trelloBoardDto.getId().isEmpty()
                        && trelloBoardDto.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> {
                            System.out.println(trelloBoardDto.getId() + " " + trelloBoardDto.getName());
                            System.out.println("This trello board contains lists: ");
                            trelloBoardDto.getLists().forEach(trelloListDto ->
                                    System.out.println(trelloListDto.getName() + " - " + trelloListDto.getId()));
                        }
                );
    }

    @PostMapping("cards")
    public CreatedTrelloCard createNewCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
    }
}
