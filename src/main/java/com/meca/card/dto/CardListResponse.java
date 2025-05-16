package com.meca.card.dto;

import com.meca.card.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardListResponse {

    private String id;

    private String front;

    private String back;

    private String folderId;

    public static CardListResponse from(Card card) {
        return CardListResponse.builder()
                .id(card.getId())
                .front(card.getFront())
                .back(card.getBack())
                .folderId(card.getFolderId())
                .build();
    }

}
