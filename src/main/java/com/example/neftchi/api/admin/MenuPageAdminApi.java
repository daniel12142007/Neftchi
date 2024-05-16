package com.example.neftchi.api.admin;

import com.example.neftchi.dto.response.MenuPageResponse;
import com.example.neftchi.dto.response.SystemMessage;
import com.example.neftchi.service.MenuPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/menu/page/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class MenuPageAdminApi {
    private final MenuPageService menuPageService;

    @GetMapping("find")
    public MenuPageResponse find() {
        return menuPageService.find();
    }

    @PutMapping("update/title/{title}")
    public MenuPageResponse updateTitle(@PathVariable String title) {
        return menuPageService.updateTitle(title);
    }

    @PutMapping("update/description/{description}")
    public MenuPageResponse updateDescription(@PathVariable String description) {
        return menuPageService.updateDescription(description);
    }

    @PutMapping("update/about/{about}")
    public MenuPageResponse updateAbout(@PathVariable String about) {
        return menuPageService.updateAbout(about);
    }

    @PutMapping("update/about/description/{description}")
    public MenuPageResponse updateAboutDescription(@PathVariable String description) {
        return menuPageService.updateAboutDescription(description);
    }

    @PutMapping("update/video/{video}")
    public MenuPageResponse updateVideo(@PathVariable String video) {
        return menuPageService.updateVideo(video);
    }

    @DeleteMapping("delete/video")
    public SystemMessage deleteVideo() {
        return menuPageService.deleteVideo();
    }
}