package com.example.neftchi.api.admin;

import com.example.neftchi.dto.response.FaqResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/faq/admin")
public class FaqAdminApi {
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

    @PostMapping(value = "save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FaqResponse faqResponse(@RequestPart MultipartFile file,
                                   @RequestParam String question,
                                   @RequestParam String answer,
                                   @RequestParam Language language
    ) {
        return faqService.save(file, question, answer, language);
    }

    @PutMapping("update/question/{id}")
    public FaqResponse updateQuestion(@PathVariable Long id,
                                      @RequestParam String question) {
        return faqService.updateQuestion(id, question);
    }

    @PutMapping("update/answer/{id}")
    public FaqResponse updateAnswer(@PathVariable Long id,
                                    @RequestParam String answer) {
        return faqService.updateAnswer(id, answer);
    }

    @DeleteMapping("delete/by/{id}")
    public List<FaqResponse> deleteById(@PathVariable Long id,
                                        @RequestParam Language language) {
        return faqService.deleteById(id, language);
    }
}