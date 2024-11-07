package com.example.finalExam.contollers;

import com.example.finalExam.miscellaneous.GetProductsQuery;
import com.example.finalExam.miscellaneous.ProductSortBy;
import com.example.finalExam.models.*;
import com.example.finalExam.services.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final GetProductService getProductService;
    private final DeleteProductService deleteProductService;
    private final GetProductsService getProductsService;
    private final CreateProductService createProductService;
    private final UpdateProductService updateProductService;

    public ProductController(GetProductService getProductService, DeleteProductService deleteProductService, GetProductsService getProductsService, CreateProductService createProductService, UpdateProductService updateProductService) {
        this.getProductService = getProductService;
        this.deleteProductService = deleteProductService;
        this.getProductsService = getProductsService;
        this.createProductService = createProductService;
        this.updateProductService = updateProductService;
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id){
        return getProductService.execute(id);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts(
            @RequestHeader(value = "region", defaultValue = "US") String region,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String nameOrDescription,
            @RequestParam(required = false) String orderBy
    ){
        return getProductsService.execute(new GetProductsQuery(Region.valueOf(region), category, nameOrDescription,
                ProductSortBy.fromValue(orderBy)));
    }


    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id){
        return deleteProductService.execute(id);
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductRequest request){
        return createProductService.execute(request);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable String id, @RequestBody ProductRequest request){
        return updateProductService.execute(new UpdateProductCommand(id, request));
    }
}
