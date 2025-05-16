package com.meca.folder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class FolderPageController {


    @GetMapping("/api/v1/folders/{folderId}")
    public String folderPage() {
        return "cards";
    }

}
