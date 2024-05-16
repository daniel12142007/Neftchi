package com.example.neftchi.service;

import com.example.neftchi.dto.request.PartnersRequest;
import com.example.neftchi.dto.response.PartnersResponse;
import com.example.neftchi.model.Partners;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.repository.PartnersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnersService {
    private final PartnersRepository partnersRepository;

    public PartnersResponse savePartners(PartnersRequest request) {
        Partners partners = Partners.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .link(request.getLink())
                .image(request.getImage())
                .language(request.getLanguage())
                .build();
        partnersRepository.save(partners);
        return partnersRepository.findByIdPartnersResponse(partners.getId());
    }

    public PartnersResponse updatePartners(PartnersRequest request, Long id) {
        Partners partners = partnersRepository.findById(id).orElseThrow();
        partners.setTitle(request.getTitle());
        partners.setCategory(request.getCategory());
        partners.setLink(request.getLink());
        partners.setImage(request.getImage());
        partners.setLanguage(request.getLanguage());
        partnersRepository.save(partners);
        return partnersRepository.findByIdPartnersResponse(id);
    }

    public List<PartnersResponse> findAllPartners(Language language) {
        return partnersRepository.findAllPartnersResponse(language);
    }

    public PartnersResponse findByIdPartners(Long id) {
        return partnersRepository.findByIdPartnersResponse(id);
    }

    public List<PartnersResponse> deleteByIdPartners(Long id, Language language) {
        partnersRepository.deleteById(id);
        return findAllPartners(language);
    }
}