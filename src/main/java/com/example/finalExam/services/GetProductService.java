package com.example.finalExam.services;
import com.example.finalExam.exceptions.ProductNotFoundException;
import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.repositories.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class GetProductService implements Query<String, ProductDTO> {
   private final ProductRepository productRepository;

   private final Logger logger = LoggerFactory.getLogger(GetProductService.class);

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<ProductDTO> execute(String id) {
        logger.info("Get Product service, {}", id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            return ResponseEntity.ok(new ProductDTO(productOptional.get()));
        }
        throw new ProductNotFoundException();
    }
}
