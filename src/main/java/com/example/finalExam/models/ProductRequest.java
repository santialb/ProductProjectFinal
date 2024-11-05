package com.example.finalExam.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProductRequest {
    private String name;
    private String description;
    private String manufacturer;
    private Double price;
    private String category;
    private String region;
}
