package com.meca.folder.controller;

import com.meca.common.dto.ApiResponse;
import com.meca.folder.dto.FolderCreateRequest;
import com.meca.folder.dto.FolderListResponse;
import com.meca.folder.dto.FolderUpdateRequest;
import com.meca.folder.service.FolderService;
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
@RequestMapping("/api/v1/folders")
public class FolderController {

    private final FolderService folderService;

    @PostMapping
    public ResponseEntity<ApiResponse> createFolder(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @RequestBody FolderCreateRequest request) {
        folderService.createFolder(userDetails.getUsername(), request);
        return ResponseEntity.ok(new ApiResponse<>("success", "success"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<FolderListResponse>>> getAllFolders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<FolderListResponse> responses = folderService.getAllFolders(userDetails.getUsername());
        return ResponseEntity.ok(new ApiResponse<>("All Folders Success", responses));
    }

    @PutMapping("/{folderId}")
    public ResponseEntity<ApiResponse> updateFolder(@PathVariable String folderId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                    @RequestBody FolderUpdateRequest request) {
        folderService.updateFolder(folderId, userDetails.getUsername(), request);
        return ResponseEntity.ok(new ApiResponse<>("success", "success"));
    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<ApiResponse> deleteFolder(@PathVariable String folderId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        folderService.deleteFolder(folderId, userDetails.getUsername());
        return ResponseEntity.ok(new ApiResponse<>("success", "success"));
    }

}
