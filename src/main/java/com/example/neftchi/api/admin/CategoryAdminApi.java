package com.example.neftchi.api.admin;

import com.example.neftchi.dto.response.CategoryResponse;
import com.example.neftchi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
@RequestMapping("api/v1/category/admin")
public class CategoryAdminApi {
    private final CategoryService categoryService;

    @GetMapping("find/all")
    public List<CategoryResponse> findAll() {
        return categoryService.findAll();
    }

    @PostMapping("save")
    public List<CategoryResponse> save(@RequestParam String category,
                                       @RequestParam String color) {
        return categoryService.save(category, color);
    }

    @PutMapping("lowest/{id}")
    public List<CategoryResponse> lowest(@PathVariable Long id) {
        return categoryService.queueNumberLowest(id);
    }

    @PutMapping("higher/{id}")
    public List<CategoryResponse> higher(@PathVariable Long id) {
        return categoryService.queueNumberHigher(id);
    }

    @DeleteMapping("delete/last/queue/number")
    public List<CategoryResponse> delete() {
        return categoryService.deleteLastQueue();
    }
}