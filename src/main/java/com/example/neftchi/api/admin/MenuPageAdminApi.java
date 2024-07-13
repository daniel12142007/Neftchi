package com.example.neftchi.api.admin;

import com.example.neftchi.dto.response.MenuPageResponse;
import com.example.neftchi.dto.response.SystemMessage;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.MenuPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/menu/page/admin")
public class MenuPageAdminApi {
    private final MenuPageService menuPageService;

    @GetMapping("find")
    public MenuPageResponse find(@RequestParam Language language) {
        return menuPageService.find(language);
    }

    @PutMapping("update/title/{title}")
    public MenuPageResponse updateTitle(@PathVariable String title,
                                        @RequestParam Language language) {
        return menuPageService.updateTitle(title, language);
    }

    @PutMapping("update/description/{description}")
    public MenuPageResponse updateDescription(@PathVariable String description,
                                              @RequestParam Language language) {
        return menuPageService.updateDescription(description, language);
    }

    @PutMapping("update/about/{about}")
    public MenuPageResponse updateAbout(@PathVariable String about,
                                        @RequestParam Language language) {
        return menuPageService.updateAbout(about, language);
    }

    @PutMapping("update/about/description/{description}")
    public MenuPageResponse updateAboutDescription(@PathVariable String description,
                                                   @RequestParam Language language) {
        return menuPageService.updateAboutDescription(description, language);
    }

    @PutMapping("update/video/{video}")
    public MenuPageResponse updateVideo(@PathVariable String video,
                                        @RequestParam Language language) {
        return menuPageService.updateVideo(video, language);
    }

    @DeleteMapping("delete/video")
    public SystemMessage deleteVideo(@RequestParam Language language) {
        return menuPageService.deleteVideo(language);
    }
}