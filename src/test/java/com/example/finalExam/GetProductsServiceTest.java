package com.example.finalExam;

import com.example.finalExam.miscellaneous.GetProductsQuery;
import com.example.finalExam.models.Category;
import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.services.GetProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GetProductsServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_no_product_matches_params_when_get_products_then_return_empty_list(){
        when(productRepository.findByNameOrDescriptionAndRegionAndCategory(
                null,null,null,null)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ProductDTO>> actual = getProductsService.execute
                (new GetProductsQuery(null, null, null, null));

        assertEquals(Collections.emptyList(), actual.getBody());
    }

    @Test
    void given_product_matches_params_when_get_products_then_return_list(){
        when(productRepository.findByNameOrDescriptionAndRegionAndCategory(any(), any(), any(), any()))
                .thenReturn(getProducts());

        ResponseEntity<List<ProductDTO>> actual = getProductsService.execute
                (new GetProductsQuery(null, null, null, null));

        List<ProductDTO> actualList = actual.getBody();

        assertEquals(2, actualList.size());
    }


    private List<Product> getProducts() {
        Product product1 = new Product();
        product1.setId("1");
        product1.setCategory(new Category("Electronics"));

        Product product2 = new Product();
        product2.setId("2");
        product2.setCategory(new Category("Electronics"));

        return Arrays.asList(product1, product2);

    }


}
