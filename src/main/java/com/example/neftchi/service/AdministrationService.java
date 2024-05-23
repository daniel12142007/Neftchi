package com.example.neftchi.service;

import com.example.neftchi.dto.request.AdministrationRequest;
import com.example.neftchi.dto.response.AdministrationResponse;
import com.example.neftchi.exception.NotFound;
import com.example.neftchi.model.Administration;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.repository.AdministrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdministrationService {
    private final AdministrationRepository administrationRepository;

    public List<AdministrationResponse> findAll(Language language) {
        return administrationRepository.findAllAdministration(language);
    }

    public AdministrationResponse findById(Long id) {
        return administrationRepository.findByIdResponse(id);
    }

    public List<AdministrationResponse> save(AdministrationRequest request) {
        Administration administration = Administration.builder()
                .fullName(request.getFullName())
                .description(request.getDescription())
                .image(request.getImage())
                .language(request.getLanguage())
                .build();
        administrationRepository.save(administration);
        return administrationRepository.findAllAdministration(request.getLanguage());
    }

    public AdministrationResponse updateFullName(Long id,
                                                 String fullName) {
        Administration administration = administrationRepository.findById(id).orElseThrow(NotFound::new);
        administration.setFullName(fullName);
        administrationRepository.save(administration);
        return administrationRepository.findByIdResponse(id);
    }

    public AdministrationResponse updateDescription(Long id,
                                                    String description) {
        Administration administration = administrationRepository.findById(id).orElseThrow(NotFound::new);
        administration.setDescription(description);
        administrationRepository.save(administration);
        return administrationRepository.findByIdResponse(id);
    }

    public AdministrationResponse updateImage(Long id,
                                              String image) {
        Administration administration = administrationRepository.findById(id).orElseThrow(NotFound::new);
        administration.setImage(image);
        administrationRepository.save(administration);
        return administrationRepository.findByIdResponse(id);
    }

    public AdministrationResponse deleteImage(Long id) {
        Administration administration = administrationRepository.findById(id).orElseThrow(NotFound::new);
        administration.setImage(null);
        administrationRepository.save(administration);
        return administrationRepository.findByIdResponse(id);
    }

    public List<AdministrationResponse> deleteById(Long id,
                                                   Language language) {
        try {
            administrationRepository.deleteById(id);
            return administrationRepository.findAllAdministration(language);
        } catch (Exception e) {
            throw new RuntimeException("Failed is delete");
        }
    }
}