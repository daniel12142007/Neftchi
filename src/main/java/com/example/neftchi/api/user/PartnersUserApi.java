package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.PartnersResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.PartnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/partners/user")
@RequiredArgsConstructor
public class PartnersUserApi {
    private final PartnersService partnersService;

    @GetMapping("find/all")
    public List<PartnersResponse> findAll(@RequestParam Language language) {
        return partnersService.findAllPartners(language);
    }

    @GetMapping("find/by/{id}")
    public PartnersResponse findById(@PathVariable Long id) {
        return partnersService.findByIdPartners(id);
    }
}