package com.example.neftchi.service;

import com.example.neftchi.dto.request.FunctionRequest;
import com.example.neftchi.dto.response.FunctionResponse;
import com.example.neftchi.exception.NotFound;
import com.example.neftchi.model.Function;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.repository.FunctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FunctionService {
    private final FunctionRepository functionRepository;

    public FunctionResponse save(FunctionRequest request) {
        Function function = Function.builder()
                .title(request.getTitle())
                .link(request.getLink())
                .image(request.getImage())
                .language(request.getLanguage())
                .build();
        functionRepository.save(function);
        return functionRepository.findByIdFunctionResponse(function.getId()).orElseThrow(NotFound::new);
    }

    public FunctionResponse update(Long id,
                                   FunctionRequest request) {
        Function function = functionRepository.findById(id).orElseThrow(NotFound::new);
        function.setTitle(request.getTitle());
        function.setLink(request.getLink());
        function.setImage(request.getImage());
        function.setLanguage(request.getLanguage());
        functionRepository.save(function);
        return functionRepository.findByIdFunctionResponse(function.getId()).orElseThrow(NotFound::new);
    }

    public FunctionResponse findById(Long id) {
        return functionRepository.findByIdFunctionResponse(id).orElseThrow(NotFound::new);
    }

    public List<FunctionResponse> findAll(Language language) {
        return functionRepository.findAllByLanguage(language);
    }

    public List<FunctionResponse> deleteById(Long id,
                                             Language language) {
        try {
            functionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("An attempt to delete failed");
        }
        return functionRepository.findAllByLanguage(language);
    }
}