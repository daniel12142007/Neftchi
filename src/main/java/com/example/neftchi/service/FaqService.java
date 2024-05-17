package com.example.neftchi.service;

import com.example.neftchi.dto.response.FaqResponse;
import com.example.neftchi.exception.NotFound;
import com.example.neftchi.model.FAQ;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {
    private final FaqRepository faqRepository;

    public FaqResponse save(MultipartFile file, String question, String answer, Language language) {
        if (answer == null || language == null
            || question == null || file == null)
            throw new RuntimeException("Fields must not be null");
        try {
            FAQ faq = FAQ.builder()
                    .language(language)
                    .data(file.getBytes())
                    .fileName(file.getOriginalFilename())
                    .answer(answer)
                    .question(question)
                    .build();
            faqRepository.save(faq);
            return faqRepository.findByIdFaqResponse(faq.getId());
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while trying to save");
        }
    }

    public FaqResponse updateQuestion(Long id,
                                      String question) {
        FAQ faq = faqRepository.findById(id).orElseThrow(NotFound::new);
        faq.setQuestion(question);
        faqRepository.save(faq);
        return faqRepository.findByIdFaqResponse(id);
    }

    public FaqResponse updateAnswer(Long id,
                                    String answer) {
        FAQ faq = faqRepository.findById(id).orElseThrow(NotFound::new);
        faq.setAnswer(answer);
        faqRepository.save(faq);
        return faqRepository.findByIdFaqResponse(id);
    }

    public List<FaqResponse> deleteById(Long id,
                                        Language language) {
        try {
            faqRepository.deleteById(id);
            return findAll(language);
        } catch (Exception e) {
            throw new NotFound();
        }
    }

    public List<FaqResponse> findAll(Language language) {
        return faqRepository.findAllFaqResponse(language);
    }

    public FaqResponse findById(Long id) {
        return faqRepository.findByIdFaqResponse(id);
    }

    public ResponseEntity<byte[]> findByPdf(Long id) {
        FAQ faq = faqRepository.findById(id).orElseThrow(NotFound::new);
        if (faq.getFileName() == null && faq.getData() == null)
            throw new NullPointerException("File is null");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + faq.getFileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(faq.getData());
    }
}