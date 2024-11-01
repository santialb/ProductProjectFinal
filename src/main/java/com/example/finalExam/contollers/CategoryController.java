package com.example.finalExam.contollers;

import com.example.finalExam.models.Category;
import com.example.finalExam.services.GetCategoriesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final GetCategoriesService getCategoriesService;

    public CategoryController(GetCategoriesService getCategoriesService) {
        this.getCategoriesService = getCategoriesService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories(){
        return getCategoriesService.execute(null);
    }

}
