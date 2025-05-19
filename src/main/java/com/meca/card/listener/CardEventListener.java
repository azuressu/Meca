package com.meca.card.listener;

import com.meca.card.entity.Card;
import com.meca.card.exception.CardException;
import com.meca.card.repository.CardRepository;
import com.meca.common.dto.FolderDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CardEventListener {

    private final CardRepository cardRepository;

    @Async
    @EventListener
    public void handleADeletedEvent(FolderDeletedEvent event) {
        try {
            String folderId = event.getFolderId();

            List<Card> cards = cardRepository.findByFolderId(folderId);

            for (Card card : cards) {
                card.updateDeletedAt(event.getDeleteTime());
                card.updateIsDeleted();
            }

            cardRepository.saveAll(cards);
        } catch (Exception e) {
            // 예외 로깅
            log.error("[이벤트 처리 실패] Folder ID: {}", event.getFolderId(), e);

            throw new CardException(CardException.CardErrorType.CARD_DELETE_ERROR);
        }
    }

}
