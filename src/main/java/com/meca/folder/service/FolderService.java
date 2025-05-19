package com.meca.folder.service;

import com.meca.common.dto.FolderDeletedEvent;
import com.meca.folder.dto.FolderCreateRequest;
import com.meca.folder.dto.FolderListResponse;
import com.meca.folder.dto.FolderUpdateRequest;
import com.meca.folder.entity.Folder;
import com.meca.folder.exception.FolderException;
import com.meca.folder.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;
    private final ApplicationEventPublisher eventPublisher;

    public void createFolder(String username, FolderCreateRequest request) {
        Folder folder = Folder.builder()
                .username(username)
                .name(request.getName())
                .build();

        folderRepository.save(folder);
    }

    public List<FolderListResponse> getAllFolders(String username) {
        List<Folder> folders = folderRepository.findByUsernameAndIsDeleted(username, false);

        List<FolderListResponse> responses = folders.stream().map(FolderListResponse::from).collect(Collectors.toList());
        return responses;
    }


    public String getFolderName(String folderId) {
        Folder folder = findFolder(folderId);
        return folder.getName();
    }

    @Transactional
    public void updateFolder(String folderId, String username, FolderUpdateRequest request) {
        Folder folder = findFolder(folderId);

        if (!folder.getUsername().equals(username)) {
            throw new FolderException(FolderException.FolderErrorType.PERMISSION_DENIED);
        }

        folder.updateFolderName(request.getName());

        folderRepository.save(folder);
    }

    @Transactional
    public void deleteFolder(String folderId, String username) {
        Folder folder = findFolder(folderId);

        if (!folder.getUsername().equals(username)) {
            throw new FolderException(FolderException.FolderErrorType.PERMISSION_DENIED);
        }

        LocalDateTime deleteTime = LocalDateTime.now();
        folder.updateDeletedAt(deleteTime);
        folder.updateIsDeleted();

        eventPublisher.publishEvent(new FolderDeletedEvent(folderId, deleteTime));

        folderRepository.save(folder);
    }

    private Folder findFolder(String folderId) {
        return folderRepository.findById(folderId)
                .orElseThrow(() -> new FolderException(FolderException.FolderErrorType.FOLDER_NOT_FOUND));
    }
}
