package com.meca.common.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FolderDeletedEvent {

    private final String folderId;
    private final LocalDateTime deleteTime;

    public FolderDeletedEvent(String folderId, LocalDateTime deleteTime) {
        this.folderId = folderId;
        this.deleteTime = deleteTime;
    }
}