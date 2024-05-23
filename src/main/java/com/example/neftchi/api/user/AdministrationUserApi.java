package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.AdministrationResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.AdministrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/administration/user")
public class AdministrationUserApi {
    private final AdministrationService administrationService;

    @GetMapping("find/all/{language}")
    public List<AdministrationResponse> findAll(@PathVariable Language language) {
        return administrationService.findAll(language);
    }

    @GetMapping("find/by/{id}")
    public AdministrationResponse findById(@PathVariable Long id) {
        return administrationService.findById(id);
    }
}
