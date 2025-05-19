package com.meca.card.controller;

import com.meca.card.dto.CardListResponse;
import com.meca.card.dto.CreateCardRequest;
import com.meca.card.service.CardService;
import com.meca.common.dto.ApiResponse;
import com.meca.security.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    @GetMapping("/{folderId}")
    public ResponseEntity<ApiResponse<List<CardListResponse>>> getAllCards(@PathVariable String folderId) {
        List<CardListResponse> response = cardService.getAllCards(folderId);
        return ResponseEntity.ok(new ApiResponse<>("success", response));
    }

    @PostMapping("/{folderId}")
    public ResponseEntity<ApiResponse> createCard(@PathVariable String folderId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  @RequestBody CreateCardRequest request) {
        cardService.createCard(folderId, userDetails.getUsername(), request);
        return ResponseEntity.ok(new ApiResponse("success", "success"));
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<ApiResponse> updateCard(@PathVariable String cardId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                  @RequestBody CreateCardRequest request) {
        cardService.updateCard(cardId, userDetails.getUsername(), request);
        return ResponseEntity.ok(new ApiResponse("success", "success"));
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<ApiResponse> deleteCard(@PathVariable String cardId,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cardService.deleteCard(cardId, userDetails.getUsername());
        return ResponseEntity.ok(new ApiResponse("success", "success"));
    }

}
