package com.example.neftchi.api.admin;

import com.example.neftchi.dto.request.PartnersRequest;
import com.example.neftchi.dto.response.PartnersResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.PartnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/partners/admin")
public class PartnersAdminApi {
    private final PartnersService partnersService;

    @GetMapping("find/all")
    public List<PartnersResponse> findAll(@RequestParam Language language) {
        return partnersService.findAllPartners(language);
    }

    @GetMapping("find/by/{id}")
    public PartnersResponse findById(@PathVariable Long id) {
        return partnersService.findByIdPartners(id);
    }

    @PostMapping("save")
    public PartnersResponse save(@RequestBody PartnersRequest request) {
        return partnersService.savePartners(request);
    }

    @PutMapping("update/{id}")
    public PartnersResponse update(@RequestBody PartnersRequest request,
                                   @PathVariable Long id) {
        return partnersService.updatePartners(request, id);
    }

    @DeleteMapping("delete/by/{id}")
    public List<PartnersResponse> deleteById(@PathVariable Long id,
                                             @RequestParam Language language) {
        return partnersService.deleteByIdPartners(id, language);
    }
}