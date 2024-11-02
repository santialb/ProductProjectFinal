package com.example.finalExam;

import com.example.finalExam.models.Product;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.services.DeleteProductService;
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

public class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_product_not_found_when_delete_product_service_then_throw_runtime_exception(){
        Mockito.when(productRepository.findById("1")).thenReturn(Optional.empty());
        Assertions.assertThrows(RuntimeException.class, () -> deleteProductService.execute("1"));
    }

    @Test
    void given_product_exists_when_delete_product_service_then_delete_product(){
        String id = "1";
        Product product = new Product();
        product.setId(id);

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(product));
        ResponseEntity<Void> actual = deleteProductService.execute(id);
        assertEquals(ResponseEntity.noContent().build(), actual);

    }


}
