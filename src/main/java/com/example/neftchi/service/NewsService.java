package com.example.neftchi.service;

import com.example.neftchi.dto.request.NewsRequest;
import com.example.neftchi.dto.response.NewsAllResponse;
import com.example.neftchi.dto.response.NewsOneResponse;
import com.example.neftchi.exception.NotFound;
import com.example.neftchi.model.Category;
import com.example.neftchi.model.News;
import com.example.neftchi.repository.CategoryRepository;
import com.example.neftchi.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;

    public List<NewsAllResponse> save(NewsRequest request, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(NotFound::new);
        News news = News.builder()
                .title(request.title())
                .description(request.description())
                .image(request.images())
                .video(request.video())
                .pdf(request.pdf())
                .youtubeLink(request.linkYoutube())
                .dataCreated(LocalDateTime.now())
                .category(category)
                .language(request.language())
                .build();
        newsRepository.save(news);
        return newsRepository.findAllNewsAllResponse();
    }

    public List<NewsAllResponse> addHeadImage(Long newsyId,
                                              String headImage) {
        News news = newsRepository.findById(newsyId).orElseThrow();
        news.setHeaderImage(headImage);
        newsRepository.save(news);
        return findAll();
    }

    public List<NewsAllResponse> findAll() {
        return newsRepository.findAllNewsAllResponse();
    }

    public List<NewsAllResponse> getNewsSortDataAsc() {
        return newsRepository.findAllResponseSortDataAsc();
    }

    public List<NewsAllResponse> getNewsSortDataDesc() {
        return newsRepository.findAllResponseSortDataDesc();
    }

    public List<NewsAllResponse> getNewsSortCategoryAsc() {
        return newsRepository.findAllResponseSortCategoryAsc();
    }

    public List<NewsAllResponse> getNewsSortCategoryDesc() {
        return newsRepository.findAllResponseSortCategoryDesc();
    }

    public List<NewsAllResponse> getNewsByCategory(String category) {
        if (categoryRepository.findByCategory(category) == null)
            throw new NullPointerException("There is no such category:" + category);
        return newsRepository.findAllResponseByCategory(category);
    }

    public NewsOneResponse findById(Long newsId) {
        return newsRepository.findByIdResponse(newsId);
    }
}