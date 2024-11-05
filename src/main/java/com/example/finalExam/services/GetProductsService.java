package com.example.finalExam.services;

import com.example.finalExam.miscellaneous.GetProductsQuery;
import com.example.finalExam.miscellaneous.ProductSortBy;
import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductDTO;
import com.example.finalExam.repositories.ProductRepository;
import com.example.finalExam.repositories.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductsService implements Query<GetProductsQuery, List<ProductDTO>> {

    private final ProductRepository productRepository;

    private final Logger logger = LoggerFactory.getLogger(GetProductsService.class);

    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public ResponseEntity<List<ProductDTO>> execute(GetProductsQuery query) {
        logger.info("Get Products Service, query: {}", query);
        Sort productSort = defineSort(query.getProductSortBy());
        Pageable pageable = PageRequest.of(0, 10, productSort);
        List<Product> productList = productRepository
                .findByNameOrDescriptionAndRegionAndCategory(query.getCategory(),
                        query.getRegion(),
                        query.getNameOrDescription(),
                        pageable);

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
