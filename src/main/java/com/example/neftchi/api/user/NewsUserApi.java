package com.example.neftchi.api.user;

import com.example.neftchi.dto.response.NewsAllResponse;
import com.example.neftchi.dto.response.NewsOneResponse;
import com.example.neftchi.model.enums.Language;
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

    @GetMapping("find/all/{language}")
    public List<NewsAllResponse> findAll(@PathVariable Language language) {
        return newsService.findAll(language);
    }

    @GetMapping("find/sort/data/asc/{language}")
    public List<NewsAllResponse> getSortDataAsc(@PathVariable Language language) {
        return newsService.getNewsSortDataAsc(language);
    }

    @GetMapping("find/sort/data/desc/{language}")
    public List<NewsAllResponse> getSortDataDesc(@PathVariable Language language) {
        return newsService.getNewsSortDataDesc(language);
    }

    @GetMapping("find/sort/category/asc/{language}")
    public List<NewsAllResponse> getSortCategoryAsc(@PathVariable Language language) {
        return newsService.getNewsSortCategoryAsc(language);
    }

    @GetMapping("find/sort/category/desc/{language}")
    public List<NewsAllResponse> getSortCategoryDesc(@PathVariable Language language) {
        return newsService.getNewsSortCategoryDesc(language);
    }

    @GetMapping("get/{category}/{language}")
    public List<NewsAllResponse> getNewsByCategory(@PathVariable String category,
                                                   @PathVariable Language language) {
        return newsService.getNewsByCategory(category, language);
    }


    @GetMapping("find/by/{newsId}")
    public NewsOneResponse findById(@PathVariable Long newsId) {
        return newsService.findById(newsId);
    }
}