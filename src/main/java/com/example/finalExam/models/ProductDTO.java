package com.example.finalExam.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ProductDTO {
private String id;
private String name;
private String description;
private Double price;
private String manufacturer;
private Category category;

public ProductDTO(Product product){
    this.id = product.getId();
    this.name = product.getName();
    this.description = product.getDescription();
    this.price = product.getPrice();
    this.manufacturer = product.getManufacturer();
    this.category = product.getCategory();
}
}
