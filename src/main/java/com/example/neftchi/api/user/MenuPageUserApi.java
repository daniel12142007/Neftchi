package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.MenuPageResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.MenuPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/menu/page/user")
@RequiredArgsConstructor
public class MenuPageUserApi {
    private final MenuPageService menuPageService;

    @GetMapping("find")
    public MenuPageResponse find(@RequestParam Language language) {
        return menuPageService.find(language);
    }
}