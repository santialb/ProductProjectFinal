package com.example.finalExam.miscellaneous;

import com.example.finalExam.models.Region;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class GetProductsQuery {
    private Region region;
    private String nameOrDescription;
    private String category;
    private ProductSortBy productSortBy;

    public GetProductsQuery(Region region, String nameOrDescription, String category, ProductSortBy productSortBy) {
        this.region = region;
        this.nameOrDescription = nameOrDescription;
        this.category = category;
        this.productSortBy = productSortBy;
    }
}
