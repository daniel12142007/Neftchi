package com.example.neftchi.api.admin;

import com.example.neftchi.dto.response.MiniPartnersResponse;
import com.example.neftchi.service.MiniPartnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/mini/partners/admin")
public class MiniPartnersAdminApi {
    private final MiniPartnersService miniPartnersService;

    @GetMapping("find/all")
    public List<MiniPartnersResponse> findAll() {
        return miniPartnersService.findAll();
    }

    @GetMapping("find/by/pdf/{id}")
    public ResponseEntity<byte[]> findByPdf(@PathVariable Long id) {
        return miniPartnersService.findByPdf(id);
    }

    @PostMapping(value = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MiniPartnersResponse save(@RequestPart MultipartFile file,
                                     @RequestParam String title) throws IOException {
        return miniPartnersService.saveMiniP(file, title);
    }

    @PutMapping("update/title/{id}")
    public List<MiniPartnersResponse> updateTitle(@PathVariable Long id,
                                                  @RequestParam String title) {
        return miniPartnersService.updateTitle(id, title);
    }

    @PutMapping(value = "update/file/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<MiniPartnersResponse> updateFile(@PathVariable Long id,
                                                 @RequestPart MultipartFile file) throws IOException {
        return miniPartnersService.updateFile(id, file);
    }

    @DeleteMapping("delete/by/{id}")
    public List<MiniPartnersResponse> deleteById(@PathVariable Long id) {
        return miniPartnersService.deleteById(id);
    }
}