package com.meca.folder.service;

import com.meca.folder.dto.FolderCreateRequest;
import com.meca.folder.dto.FolderListResponse;
import com.meca.folder.entity.Folder;
import com.meca.folder.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    public void createFolder(String username, FolderCreateRequest request) {
        Folder folder = Folder.builder()
                .username(username)
                .name(request.getName())
                .build();

        folderRepository.save(folder);
    }

    public List<FolderListResponse> getAllFolders(String username) {
        List<Folder> folders = folderRepository.findByUsername(username);

        List<FolderListResponse> responses = folders.stream().map(FolderListResponse::from).collect(Collectors.toList());
        return responses;
    }


}
