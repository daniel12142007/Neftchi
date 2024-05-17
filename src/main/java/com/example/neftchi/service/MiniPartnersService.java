package com.example.neftchi.service;

import com.example.neftchi.dto.response.MiniPartnersResponse;
import com.example.neftchi.exception.NotFound;
import com.example.neftchi.model.MiniPartners;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.repository.MiniPartnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MiniPartnersService {
    private final MiniPartnersRepository miniPartnersRepository;

    public MiniPartnersResponse saveMiniP(MultipartFile file,
                                          String title) throws IOException {
        if (file == null)
            throw new NullPointerException("The file must not be empty");
        if (title == null)
            throw new NullPointerException("The title must not be empty");
        MiniPartners miniPartners = MiniPartners.builder()
                .title(title)
                .pdf(file.getOriginalFilename())
                .data(file.getBytes())
                .build();
        miniPartnersRepository.save(miniPartners);
        return miniPartnersRepository.findByIdMiniPartnersResponse(miniPartners.getId()).orElseThrow(NotFound::new);
    }

    public List<MiniPartnersResponse> updateTitle(Long id,
                                                  String title) {
        MiniPartners miniPartners = miniPartnersRepository.findById(id).orElseThrow(NotFound::new);
        try {
            miniPartners.setTitle(title);
            miniPartnersRepository.save(miniPartners);
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException("An attempt to update a title failed");
        }
    }

    public List<MiniPartnersResponse> updateFile(Long id,
                                                 MultipartFile file) throws IOException {
        MiniPartners miniPartners = miniPartnersRepository.findById(id).orElseThrow(NotFound::new);
        try {
            miniPartners.setData(file.getBytes());
            miniPartners.setPdf(file.getOriginalFilename());
            miniPartnersRepository.save(miniPartners);
            return findAll();
        } catch (Exception e) {
            throw new RuntimeException("An attempt to update a file failed");
        }
    }

    public List<MiniPartnersResponse> findAll() {
        return miniPartnersRepository.findAllMiniPartnersResponse();
    }

    public List<MiniPartnersResponse> deleteById(Long id) {
        try {
            miniPartnersRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("The object will not be found");
        }
        return miniPartnersRepository.findAllMiniPartnersResponse();
    }

    public ResponseEntity<byte[]> findByPdf(Long id) {
        MiniPartners miniPartners = miniPartnersRepository.findById(id).orElseThrow(NotFound::new);
        if (miniPartners.getPdf() == null && miniPartners.getData() == null)
            throw new NullPointerException("File is null");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + miniPartners.getPdf() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                .body(miniPartners.getData());
    }
}