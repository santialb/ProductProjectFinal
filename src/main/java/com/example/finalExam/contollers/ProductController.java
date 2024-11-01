package com.example.finalExam.contollers;

import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.services.GetProductService;
import com.example.finalExam.services.SearchProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    private final GetProductService getProductService;
    private final SearchProductsService searchProductsService;

    public ProductController(GetProductService getProductService, SearchProductsService searchProductsService) {
        this.getProductService = getProductService;
        this.searchProductsService = searchProductsService;
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        return getProductService.execute(id);
    }

    @GetMapping("product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name){
        return searchProductsService.execute(name);
    }
}
