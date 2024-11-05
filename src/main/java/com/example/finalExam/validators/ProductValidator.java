package com.example.finalExam.validators;

import com.example.finalExam.exceptions.InvalidProductException;
import com.example.finalExam.models.Category;
import com.example.finalExam.models.Product;
import com.example.finalExam.models.ProductRequest;
import com.example.finalExam.models.Region;

import java.util.List;

public class ProductValidator {

    public static Product execute(ProductRequest productRequest, List<Category> availableCategories){

        if (categoryNotAvailable(productRequest.getCategory(), availableCategories)){
            throw new InvalidProductException();
        }

        if (regionNotAvailable(productRequest.getRegion())){
            throw new InvalidProductException();
        }

        if (ProfanityValidator.hasProfanity(productRequest.getName(), productRequest.getDescription())){
            throw new InvalidProductException();
        }

        return new Product(productRequest);

    }

    private static boolean categoryNotAvailable(String category, List<Category> availableCategories) {
        return !availableCategories.contains(new Category(category));
    }

    private static boolean regionNotAvailable(String candidate) {
            for (Region region: Region.values()){
                if (region.name().equals(candidate)){
                    return false;
                }
            }
            return true;
    }

    
}
