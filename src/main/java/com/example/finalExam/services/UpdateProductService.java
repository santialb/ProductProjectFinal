package com.example.finalExam.services;

import com.example.finalExam.exceptions.ProductNotFoundException;
import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.models.UpdateProductCommand;
import com.example.finalExam.repositories.CategoryRepository;
import com.example.finalExam.repositories.Command;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.validators.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductService implements Command<UpdateProductCommand, ProductDTO> {

    private final Logger logger = LoggerFactory.getLogger(UpdateProductService.class);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public UpdateProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand command) {
        logger.info("Update Product Service {} {} {}", command.getId(), command.getProductRequest(), getClass().getSimpleName());
        Optional<Product> productOptional = productRepository.findById(command.getId());
        if (productOptional.isPresent()) {
            Product product = ProductValidator.execute(command.getProductRequest(), categoryRepository.findAll());
            product.setId(command.getId());
            product.setCreatedAt(productOptional.get().getCreatedAt());
            productRepository.save(product);
            return ResponseEntity.ok(new ProductDTO(product));
        }
        throw new ProductNotFoundException();
    }
}
