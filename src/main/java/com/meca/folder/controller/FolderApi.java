package com.meca.folder.controller;

import com.meca.folder.dto.FolderCreateRequest;
import com.meca.folder.dto.FolderListResponse;
import com.meca.folder.dto.FolderUpdateRequest;
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

@Tag(name = "폴더 API", description = "암기 폴더 API 정리")
public interface FolderApi {

    @Operation(summary = "폴더 생성", description = "폴더 생성 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "폴더 생성 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse> createFolder(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                 @RequestBody FolderCreateRequest request);

    @Operation(summary = "폴더 전체 조회", description = "폴더 전체 조회 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "폴더 전체 조회 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse<List<FolderListResponse>>> getAllFolders(@AuthenticationPrincipal UserDetailsImpl userDetails);

    @Operation(summary = "폴더 수정", description = "폴더 수정 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "폴더 수정 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse> updateFolder(@PathVariable String folderId,
                                                                 @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                                 @RequestBody FolderUpdateRequest request);

    @Operation(summary = "폴더 삭제", description = "폴더 삭제 API")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "폴더 삭제 성공",
                            content = @Content(schema = @Schema(implementation = ApiResponse.class)))
            }
    )
    ResponseEntity<com.meca.common.dto.ApiResponse> deleteFolder(@PathVariable String folderId,
                                                                 @AuthenticationPrincipal UserDetailsImpl userDetails);


}
