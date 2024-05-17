package com.example.neftchi.api.admin;

import com.example.neftchi.dto.request.FunctionRequest;
import com.example.neftchi.dto.response.FunctionResponse;
import com.example.neftchi.model.enums.Language;
import com.example.neftchi.service.FunctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/function/admin")
public class FunctionAdminApi {
    private final FunctionService functionService;

    @GetMapping("find/all")
    public List<FunctionResponse> findAll(@RequestParam Language language) {
        return functionService.findAll(language);
    }

    @GetMapping("find/by/{id}")
    public FunctionResponse findById(@PathVariable Long id) {
        return functionService.findById(id);
    }

    @PostMapping("save")
    public FunctionResponse save(@RequestBody FunctionRequest request) {
        return functionService.save(request);
    }

    @PutMapping("update/{id}")
    public FunctionResponse update(@PathVariable Long id,
                                   @RequestBody  FunctionRequest request) {
        return functionService.update(id, request);
    }

    @DeleteMapping("delete/by/{id}")
    public List<FunctionResponse> deleteById(@PathVariable Long id,
                                             @RequestParam Language language) {
        return functionService.deleteById(id, language);
    }
}