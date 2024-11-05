package com.example.finalExam.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private String id;


    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @PositiveOrZero(message = "Price must not be negative")
    @Column(name = "price")
    private Double price;

    @Column(name = "manufacturer")
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "category_value", referencedColumnName = "value")
    private Category category;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "region")
    @Enumerated(EnumType.STRING)
    private Region region;

    public Product(ProductRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.manufacturer = request.getManufacturer();
        this.price = request.getPrice();
        this.category = new Category(request.getCategory());
        this.region = Region.valueOf(request.getRegion());
    }
}
