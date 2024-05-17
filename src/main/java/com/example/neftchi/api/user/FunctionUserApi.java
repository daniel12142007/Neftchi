package com.example.neftchi.api.user;


import com.example.neftchi.dto.response.FunctionResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.FunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/function/user")
public class FunctionUserApi {
    private final FunctionService functionService;

    @GetMapping("find/all")
    public List<FunctionResponse> findAll(@RequestParam Language language) {
        return functionService.findAll(language);
    }

    @GetMapping("find/by/{id}")
    public FunctionResponse findById(@PathVariable Long id) {
        return functionService.findById(id);
    }
}