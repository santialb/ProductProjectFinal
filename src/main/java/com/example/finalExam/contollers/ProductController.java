package com.example.finalExam.contollers;

import com.example.finalExam.miscellaneous.GetProductsQuery;
import com.example.finalExam.miscellaneous.ProductSortBy;
import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.models.Region;
import com.example.finalExam.services.DeleteProductService;
import com.example.finalExam.services.GetProductService;
import com.example.finalExam.services.GetProductsService;
import com.example.finalExam.services.SearchProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final GetProductService getProductService;
    private final SearchProductsService searchProductsService;
    private final DeleteProductService deleteProductService;
    private final GetProductsService getProductsService;

    public ProductController(GetProductService getProductService, SearchProductsService searchProductsService, DeleteProductService deleteProductService, GetProductsService getProductsService) {
        this.getProductService = getProductService;
        this.searchProductsService = searchProductsService;
        this.deleteProductService = deleteProductService;
        this.getProductsService = getProductsService;
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

    @GetMapping("product/search")
    public ResponseEntity<List<ProductDTO>> searchProductByName(@RequestParam String name){
        return searchProductsService.execute(name);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id){
        return deleteProductService.execute(id);
    }
}
