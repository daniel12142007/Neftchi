package com.example.neftchi.api.admin;

import com.example.neftchi.dto.request.AdministrationRequest;
import com.example.neftchi.dto.response.AdministrationResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.AdministrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/administration/admin")
public class AdministrationAdminApi {
    private final AdministrationService administrationService;

    @GetMapping("find/all/{language}")
    public List<AdministrationResponse> findAll(@PathVariable Language language) {
        return administrationService.findAll(language);
    }

    @GetMapping("find/by/{id}")
    public AdministrationResponse findById(@PathVariable Long id) {
        return administrationService.findById(id);
    }

    @PostMapping("save")
    public List<AdministrationResponse> save(@RequestBody AdministrationRequest request) {
        return administrationService.save(request);
    }

    @PutMapping("update/full/name/{id}")
    public AdministrationResponse updateFullName(@PathVariable Long id,
                                                 @RequestParam String fullName) {
        return administrationService.updateFullName(id, fullName);
    }

    @PutMapping("update/description/{id}")
    public AdministrationResponse updateDescription(@PathVariable Long id,
                                                    @RequestParam String description) {
        return administrationService.updateDescription(id, description);
    }

    @PutMapping("update/image/{id}")
    public AdministrationResponse updateImage(@PathVariable Long id,
                                              @RequestParam String image) {
        return administrationService.updateImage(id, image);
    }

    @DeleteMapping("delete/image/{id}")
    public AdministrationResponse deleteImage(@PathVariable Long id) {
        return administrationService.deleteImage(id);
    }

    @DeleteMapping("delete/by/{id}")
    public List<AdministrationResponse> deleteById(@PathVariable Long id,
                                                   @RequestParam Language language) {
        return administrationService.deleteById(id, language);
    }
}