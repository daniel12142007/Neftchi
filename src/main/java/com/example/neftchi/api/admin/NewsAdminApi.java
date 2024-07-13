package com.example.neftchi.api.admin;

import com.example.neftchi.dto.request.NewsRequest;
import com.example.neftchi.dto.response.NewsAllResponse;
import com.example.neftchi.dto.response.NewsOneResponse;
import com.example.neftchi.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/news/admin")
public class NewsAdminApi {
    private final NewsService newsService;

    @PostMapping("save/{categoryId}")
    public List<NewsAllResponse> save(@PathVariable Long categoryId,
                                      @RequestBody NewsRequest request) {
        return newsService.save(request, categoryId);
    }

    @PostMapping("add/head/image/{newsId}")

    public List<NewsAllResponse> addHead(@PathVariable Long newsId,
                                         @RequestParam String headImage) {
        return newsService.addHeadImage(newsId, headImage);
    }

    @GetMapping("find/all")
    public List<NewsAllResponse> findAll() {
        return newsService.findAll();
    }

    @GetMapping("find/sort/data/asc")
    public List<NewsAllResponse> getSortDataAsc() {
        return newsService.getNewsSortDataAsc();
    }

    @GetMapping("find/sort/data/desc")
    public List<NewsAllResponse> getSortDataDesc() {
        return newsService.getNewsSortDataDesc();
    }

    @GetMapping("find/sort/category/asc")
    public List<NewsAllResponse> getSortCategoryAsc() {
        return newsService.getNewsSortCategoryAsc();
    }

    @GetMapping("find/sort/category/desc")
    public List<NewsAllResponse> getSortCategoryDesc() {
        return newsService.getNewsSortCategoryDesc();
    }

    @GetMapping("get/{category}")
    public List<NewsAllResponse> getNewsByCategory(@PathVariable String category) {
        return newsService.getNewsByCategory(category);
    }

    @GetMapping("find/by/{newsId}")
    public NewsOneResponse findById(@PathVariable Long newsId) {
        return newsService.findById(newsId);
    }

}
