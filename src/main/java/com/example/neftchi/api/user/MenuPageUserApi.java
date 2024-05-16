package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.MenuPageResponse;
import com.example.neftchi.service.MenuPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/menu/page/user")
@RequiredArgsConstructor
public class MenuPageUserApi {
    private final MenuPageService menuPageService;

    @GetMapping("find")
    public MenuPageResponse find() {
        return menuPageService.find();
    }
}