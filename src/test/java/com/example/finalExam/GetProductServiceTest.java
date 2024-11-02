package com.example.finalExam;

import com.example.finalExam.exceptions.ProductNotFoundException;
import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.services.GetProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductService getProductService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_product_not_found_when_get_product_service_then_throw_product_not_found_exception(){
        Mockito.when(productRepository.findById("1")).thenReturn(Optional.empty());
        Assertions.assertThrows(ProductNotFoundException.class, () -> getProductService.execute("1"));
    }

    @Test
    void given_product_exists_when_get_product_service_then_return_product(){
        String id = "1";
        Product product = new Product();
        product.setId(id);

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
        ResponseEntity<ProductDTO> actual = getProductService.execute(id);
        assertEquals(ResponseEntity.ok().body(new ProductDTO(product)), actual);

    }
}
