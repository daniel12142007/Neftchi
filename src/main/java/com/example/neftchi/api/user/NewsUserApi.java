package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.NewsAllResponse;
import com.example.neftchi.dto.response.NewsOneResponse;
import com.example.neftchi.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/news/user")
public class NewsUserApi {
    private final NewsService newsService;

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