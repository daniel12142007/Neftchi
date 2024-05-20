package com.example.neftchi.service;

import com.example.neftchi.dto.response.SystemMessage;
import com.example.neftchi.exception.NotFound;
import com.example.neftchi.model.Appeal;
import com.example.neftchi.model.enums.StatusAppeal;
import com.example.neftchi.repository.AppealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppealService {
    private final AppealRepository appealRepository;

    public SystemMessage save(String fullName,
                              String briefComplaint,
                              String phoneNumber,
                              String email,
                              String description,
                              MultipartFile file) throws IOException {
        Appeal appeal = Appeal.builder()
                .fullName(fullName)
                .briefComplaint(briefComplaint)
                .phoneNumber(phoneNumber)
                .email(email)
                .description(description)
                .fileName(file.getOriginalFilename())
                .data(file.getBytes())
                .status(StatusAppeal.UNANSWERED)
                .dataCreated(LocalDateTime.now())
                .build();
        appealRepository.save(appeal);
        return new SystemMessage("Request saved successfully");
    }

    public ResponseEntity<byte[]> findByPdf(Long id) {
        Appeal appeal = appealRepository.findById(id).orElseThrow(NotFound::new);
        if (appeal.getFileName() == null && appeal.getData() == null)
            throw new NullPointerException("File is null");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + appeal.getFileName() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(appeal.getData());
    }
}