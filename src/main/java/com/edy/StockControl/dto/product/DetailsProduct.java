package com.edy.StockControl.dto.product;

import com.edy.StockControl.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DetailsProduct(

        String sku,
        String name,
        String description,
        BigDecimal price,
        Integer quantity,
        LocalDateTime created,
        LocalDateTime updated,
        BigDecimal calc
) {

    public static DetailsProduct fromEntity(Product product) {
        return new DetailsProduct(
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCreated(),
                product.getUpdated(),
                product.getCalc()
        );
    }
}
