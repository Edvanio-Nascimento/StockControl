package com.edy.StockControl.dto.product;

import com.edy.StockControl.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockProduct(

        String sku,
        String name,
        BigDecimal price,
        Integer quantity,
        BigDecimal calc
) {

    public static StockProduct fromEntity(Product product) {
        return new StockProduct(
                product.getSku(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCalc()
        );
    }
}
