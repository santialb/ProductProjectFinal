package com.example.finalExam.services;
import com.example.finalExam.exceptions.ProductNotFoundException;
import com.example.finalExam.models.Product;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.repositories.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetProductService implements Query<String, Product> {
   private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<Product> execute(String input) {
        Optional<Product> productOptional = productRepository.findById(input);
        if (productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.get());
        }
        throw new ProductNotFoundException();
    }
}
