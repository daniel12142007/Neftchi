package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.MiniPartnersResponse;
import com.example.neftchi.service.MiniPartnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/mini/partners/user")
public class MiniPartnersUserApi {
    private final MiniPartnersService miniPartnersService;

    @GetMapping("find/all")
    public List<MiniPartnersResponse> findAll() {
        return miniPartnersService.findAll();
    }

    @GetMapping("find/by/pdf/{id}")
    public ResponseEntity<byte[]> findByPdf(@PathVariable Long id) {
        return miniPartnersService.findByPdf(id);
    }
}