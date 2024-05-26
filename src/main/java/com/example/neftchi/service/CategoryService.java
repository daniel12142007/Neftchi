package com.example.neftchi.service;

import com.example.neftchi.dto.request.CategoryRequest;
import com.example.neftchi.dto.response.CategoryResponse;
import com.example.neftchi.exception.NotFound;
import com.example.neftchi.model.Category;
import com.example.neftchi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> save(CategoryRequest request) {
        Category category = Category.builder()
                .category(request.getCategory())
                .color(request.getColor())
                .queueNumber(categoryRepository.findAllResponse().size() + 1)
                .build();
        categoryRepository.save(category);
        return categoryRepository.findAllResponse();
    }

    public List<CategoryResponse> queueNumberHigher(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(NotFound::new);
        if (category.getQueueNumber() <= 1)
            throw new RuntimeException("He's the highest number in line");
        Category category1 = categoryRepository.findByQueueNumber(category.getQueueNumber() - 1);
        int queueNumber1 = category1.getQueueNumber();
        int queueNumber2 = category.getQueueNumber();
        category1.setQueueNumber(queueNumber2);
        categoryRepository.save(category1);
        category.setQueueNumber(queueNumber1);
        categoryRepository.save(category);
        return categoryRepository.findAllResponse();
    }

    public List<CategoryResponse> queueNumberLowest(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(NotFound::new);
        if (category.getQueueNumber() >= categoryRepository.findAllResponse().size())
            throw new RuntimeException("He's the lowest number in line");
        Category category1 = categoryRepository.findByQueueNumber(category.getQueueNumber() + 1);
        int queueNumber1 = category1.getQueueNumber();
        int queueNumber2 = category.getQueueNumber();
        category1.setQueueNumber(queueNumber2);
        categoryRepository.save(category1);
        category.setQueueNumber(queueNumber1);
        categoryRepository.save(category);
        return categoryRepository.findAllResponse();
    }

    public List<CategoryResponse> save(String category,
                                       String color) {
        Category category1 = Category.builder()
                .category(category)
                .color(color)
                .queueNumber(categoryRepository.findAllResponse().size() + 1)
                .build();
        categoryRepository.save(category1);
        return categoryRepository.findAllResponse();
    }

    public List<CategoryResponse> deleteLastQueue() {
        if (categoryRepository.findAllResponse().isEmpty())
            throw new RuntimeException("No categories left");
        Category category = categoryRepository.findByQueueNumber(categoryRepository.findAllResponse().size());
        categoryRepository.delete(category);
        return categoryRepository.findAllResponse();
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAllResponse();
    }
}