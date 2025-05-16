package com.meca.folder.dto;

import com.meca.folder.entity.Folder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FolderListResponse {

    private String id;

    private String username;

    private String name;

    public static FolderListResponse from(Folder folder) {
        return FolderListResponse.builder()
                .id(folder.getId())
                .username(folder.getUsername())
                .name(folder.getName())
                .build();
    }

}
