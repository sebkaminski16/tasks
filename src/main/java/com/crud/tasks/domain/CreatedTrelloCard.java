package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreatedTrelloCard {
    private String id;
    private String name;
    private String shortUrl;
    private TrelloBadgesDto badges;

    public CreatedTrelloCard(String id, String name, String shortUrl) {
        this.id = id;
        this.name = name;
        this.shortUrl = shortUrl;
    }
}
