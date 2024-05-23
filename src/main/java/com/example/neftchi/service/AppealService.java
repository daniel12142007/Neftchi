package com.example.neftchi.service;

import com.example.neftchi.dto.response.AppealResponseAll;
import com.example.neftchi.dto.response.AppealResponseOne;
import com.example.neftchi.dto.response.SystemMessage;
import com.example.neftchi.exception.NotFound;
import com.example.neftchi.model.Appeal;
import com.example.neftchi.model.enums.StatusAppeal;
import com.example.neftchi.repository.AppealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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

    public List<AppealResponseAll> findAll11() {
        return appealRepository.findBySomeFieldWithPageable(PageRequest.of(0, 11));
    }

    public List<AppealResponseAll> findAll() {
        return appealRepository.findAllAppealResponseAll();
    }

    public List<AppealResponseAll> findSortNameAsc() {
        return appealRepository.findAllSortNameAsc();
    }

    public List<AppealResponseAll> findSortNameDesc() {
        return appealRepository.findAllSortNameDesc();
    }

    public List<AppealResponseAll> findSortEmailAsc() {
        return appealRepository.findAllSortEmailAsc();
    }

    public List<AppealResponseAll> findSortEmailDesc() {
        return appealRepository.findAllSortEmailDesc();
    }

    public List<AppealResponseAll> findSortNumberAsc() {
        return appealRepository.findAllSortNumberAsc();
    }

    public List<AppealResponseAll> findSortNumberDesc() {
        return appealRepository.findAllSortNumberDesc();
    }

    public List<AppealResponseAll> findSortStatusAsc() {
        return appealRepository.findAllSortStatusAsc();
    }

    public List<AppealResponseAll> findSortStatusDesc() {
        return appealRepository.findAllSortStatusDesc();
    }

    public AppealResponseOne saveAnswer(Long id,
                                        String answer) {
        Appeal appeal = appealRepository.findById(id).orElseThrow(NotFound::new);
        if (appeal.getStatus() == StatusAppeal.ANSWERED)
            throw new RuntimeException("This appeal has already been answered");
        appeal.setAppealAnswer(answer);
        appeal.setStatus(StatusAppeal.ANSWERED);
        appealRepository.save(appeal);
        return appealRepository.findByIdAppealResponseOne(id);
    }

    public AppealResponseOne findById(Long id) {
        return appealRepository.findByIdAppealResponseOne(id);
    }

    public List<AppealResponseAll> deleteById(Long id) {
        try {
            appealRepository.deleteById(id);
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete");
        }
    }
}