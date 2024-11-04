package com.example.finalExam.services;

import com.example.finalExam.miscellaneous.GetProductsQuery;
import com.example.finalExam.miscellaneous.ProductSortBy;
import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.repositories.Query;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsService implements Query<GetProductsQuery, List<ProductDTO>> {

    private final ProductRepository productRepository;

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<List<ProductDTO>> execute(GetProductsQuery query) {

        System.out.println("nameOrDescription: " + query.getNameOrDescription());
        System.out.println("category: " + query.getCategory());
        System.out.println("region: " + query.getRegion());
        System.out.println("sort: " + defineSort(query.getProductSortBy()));

        Sort productSort = defineSort(query.getProductSortBy());
        List<Product> productList = productRepository
                .findByNameOrDescriptionAndRegionAndCategory(query.getCategory(),
                        query.getRegion(),
                        query.getNameOrDescription(),
                        productSort);

        return ResponseEntity.ok(productList.stream().map(ProductDTO::new).toList());

    }

    private Sort defineSort(ProductSortBy productSortBy) {
        if (productSortBy == null){
            return Sort.unsorted();
        }
        ProductSortBy sortBy = ProductSortBy.valueOf(productSortBy.getValue());
        return Sort.by(String.valueOf(sortBy));
    }
}
