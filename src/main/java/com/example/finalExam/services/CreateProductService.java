package com.example.finalExam.services;

import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.models.ProductRequest;
import com.example.finalExam.repositories.CategoryRepository;
import com.example.finalExam.repositories.Command;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.validators.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService implements Command<ProductRequest, ProductDTO> {

    private final ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(CreateProductService.class);
    private final CategoryRepository categoryRepository;

    public CreateProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public ResponseEntity<ProductDTO> execute(ProductRequest request) {
        logger.info("Create Product Service {} {}", request, getClass().getSimpleName());
       Product validatedProduct = ProductValidator.execute(request, categoryRepository.findAll());
       productRepository.save(validatedProduct);

    return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(validatedProduct));

    }
}
