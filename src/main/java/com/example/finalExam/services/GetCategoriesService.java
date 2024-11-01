package com.example.finalExam.services;

import com.example.finalExam.models.Category;
import com.example.finalExam.repositories.CategoryRepository;
import com.example.finalExam.repositories.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetCategoriesService implements Query<Void, List<String>> {

    private final CategoryRepository categoryRepository;

    private final Logger logger = LoggerFactory.getLogger(GetCategoriesService.class);

    public GetCategoriesService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<List<String>> execute(Void input) {
        logger.info("Executing {} GetCategoriesService", getClass().getSimpleName());
        return ResponseEntity.ok(categoryRepository.findAll().stream()
                .map(Category::getValue).collect(Collectors.toList()));
    }
}
