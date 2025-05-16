package com.meca.card.service;

import com.meca.card.dto.CardListResponse;
import com.meca.card.dto.CreateCardRequest;
import com.meca.card.entity.Card;
import com.meca.card.exception.CardException;
import com.meca.card.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public List<CardListResponse> getAllCards(String folderId) {
        List<Card> cards = cardRepository.findByFolderId(folderId);
        List<CardListResponse> responses = cards.stream().map(CardListResponse::from).toList();
        return responses;
    }

    public void createCard(String folderId, String username, CreateCardRequest request) {
        Card card = Card.builder()
                .front(request.getFront())
                .back(request.getBack())
                .username(username)
                .folderId(folderId)
                .memorised(false)
                .build();

        cardRepository.save(card);
    }

    @Transactional
    public void updateCard(String cardId, String username, CreateCardRequest request) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new CardException(CardException.CardErrorType.CARD_NOT_FOUND));

        if (!card.getUsername().equals(username)) {
            throw new CardException(CardException.CardErrorType.PERMISSION_DENIED);
        }

        card.updateCardInfo(request.getFront(), request.getBack());
        cardRepository.save(card);
    }
}
