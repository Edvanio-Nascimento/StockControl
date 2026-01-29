package com.edy.StockControl.repository;

import com.edy.StockControl.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    boolean existsBySku(@NotBlank(message = "SKU é necessário")
            @Size(max = 20, message = "SKU deve ter no máximo 20 caracteres")
            String sku);

    Optional<Product> findBySku(String sku);
}
