package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.FaqResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/faq/User")
public class FaqUserApi {
    private final FaqService faqService;

    @GetMapping("find/all")
    public List<FaqResponse> findAll(@RequestParam Language language) {
        return faqService.findAll(language);
    }

    @GetMapping("find/by/{id}")
    public FaqResponse findById(@PathVariable Long id) {
        return faqService.findById(id);
    }

    @GetMapping("find/by/pdf/{id}")
    public ResponseEntity<byte[]> findByPdf(@PathVariable Long id) {
        return faqService.findByPdf(id);
    }
}