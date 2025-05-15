package com.meca.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class PageController {

    @GetMapping("/")
    public String mainPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) {
//            String username = userDetails.getUsername();
//
//            // 예를 들어, 사용자 폴더 리스트를 서비스에서 가져온다고 가정
//            List<Folder> folders = folderService.getFoldersByUsername(username);
//
//            model.addAttribute("username", username);
//            model.addAttribute("folders", folders);
        }
        return "folder";  // folder.html 뷰 리턴
    }

}
