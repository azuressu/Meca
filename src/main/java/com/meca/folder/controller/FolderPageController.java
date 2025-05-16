package com.meca.folder.controller;

import com.meca.folder.service.FolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FolderPageController {

    private final FolderService folderService;

    @GetMapping("/api/v1/folders/{folderId}")
    public String folderPage(Model model, @PathVariable String folderId) {
        String folderName = folderService.getFolderName(folderId);
        model.addAttribute("folderName", folderName);
        return "cards";
    }

}
