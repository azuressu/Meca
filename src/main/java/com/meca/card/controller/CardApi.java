package com.meca.card.controller;

import com.meca.card.dto.CardListResponse;
import com.meca.card.dto.CardMemorizedRequest;
import com.meca.card.dto.CreateCardRequest;
import com.meca.security.impl.UserDetailsImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "카드 API", description = "암기 카드 API 정리")
public interface CardApi {

    @Operation(summary = "카드 전체 조회", description = "카드 전체 조회 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "카드 전체 조회 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse<List<CardListResponse>>> getAllCards(@PathVariable String folderId);

    @Operation(summary = "카드 생성", description = "카드 생성 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "카드 전체 조회 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse> createCard(@PathVariable String folderId,
                                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                @RequestBody CreateCardRequest request);

    @Operation(summary = "카드 수정", description = "카드 수정 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "카드 수정 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse> updateCard(@PathVariable String cardId,
                                                               @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                               @RequestBody CreateCardRequest request) ;

    @Operation(summary = "카드 삭제", description = "카드 삭제 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "카드 삭제 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse> deleteCard(@PathVariable String cardId,
                                                                                        @AuthenticationPrincipal UserDetailsImpl userDetails);

    @Operation(summary = "카드 암기 여부 수정", description = "카드 암기 여부 수정 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "카드 암기 여부 수정 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse> updateMemorizedCard(@PathVariable String cardId,
                                                               @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                               @RequestBody CardMemorizedRequest request) ;


}
