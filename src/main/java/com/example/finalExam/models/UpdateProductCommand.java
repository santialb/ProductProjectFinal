package com.example.finalExam.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateProductCommand {
    private String id;
    private ProductRequest productRequest;
}
