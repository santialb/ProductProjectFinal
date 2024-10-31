package com.example.finalExam.product.model;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "product")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "category")
    private String category;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "region")
    private Region region;
}
