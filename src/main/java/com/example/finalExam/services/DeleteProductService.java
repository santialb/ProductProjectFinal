package com.example.finalExam.services;

import com.example.finalExam.exceptions.ProductNotFoundException;
import com.example.finalExam.models.Product;
import com.example.finalExam.repositories.Command;
import com.example.finalExam.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class DeleteProductService implements Command<String, Void> {
    private final ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<Void> execute(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()){
            productRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        throw new ProductNotFoundException();
    }
}
