package com.example.finalExam.services;

import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.repositories.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchProductsService implements Query<String, List<ProductDTO>> {

    private final ProductRepository productRepository;

    public SearchProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String input) {
        return ResponseEntity.ok(productRepository.findByNameContaining(input)
                .stream().map(ProductDTO::new).toList());
    }
}
