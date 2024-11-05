package com.example.finalExam.repositories;

import com.example.finalExam.models.Product;
import com.example.finalExam.models.Region;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p WHERE " +
            "( :nameOrDescription IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :nameOrDescription, '%')) " +
            "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :nameOrDescription, '%'))) AND " +
            "p.region = :region AND " +
            "( :category IS NULL OR LOWER(p.category.value) = LOWER(:category) )")
    List<Product> findByNameOrDescriptionAndRegionAndCategory(
            String nameOrDescription, Region region, String category, Pageable pageable);

}
