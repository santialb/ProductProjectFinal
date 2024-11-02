package com.example.finalExam;

import com.example.finalExam.models.Category;
import com.example.finalExam.repositories.CategoryRepository;
import com.example.finalExam.services.GetCategoriesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetCategoriesServiceTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private GetCategoriesService getCategoriesService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_products_exist_when_get_categories_service_then_return_list(){
        List<Category> categoryList = Arrays.asList(
                new Category("Electronics"),
                new Category("Bathroom"),
                new Category("Automobile")
        );

        Mockito.when(categoryRepository.findAll()).thenReturn(categoryList);
        List<String> expected = Arrays.asList("Electronics", "Bathroom", "Automobile");
        ResponseEntity<List<String>> actual = getCategoriesService.execute(null);
        assertEquals(ResponseEntity.ok(expected), actual);
    }

    @Test
    void given_products_does_not_exist_when_get_categories_service_then_return_empty_list(){
        Mockito.when(categoryRepository.findAll()).thenReturn(Collections.emptyList());
        List<String> expected = Collections.emptyList();
        ResponseEntity<List<String>> actual = getCategoriesService.execute(null);
        assertEquals(ResponseEntity.ok(expected), actual);
    }

}
