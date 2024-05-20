package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.SystemMessage;
import com.example.neftchi.service.AppealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/appeal/user")
public class AppealUserApi {
    private final AppealService appealService;

    @PostMapping(value = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SystemMessage save(@RequestPart MultipartFile file,
                              @RequestParam String fullName,
                              @RequestParam String briefComplaint,
                              @RequestParam String phoneNumber,
                              @RequestParam String email,
                              @RequestParam(defaultValue = "No description") String description) throws IOException {
        return appealService.save(fullName,
                briefComplaint,
                phoneNumber,
                email,
                description,
                file);
    }

    @GetMapping("find/by/pdf/{id}")
    public ResponseEntity<byte[]> findByPdf(@PathVariable Long id) {
        return appealService.findByPdf(id);
    }
}