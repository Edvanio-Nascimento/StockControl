package com.edy.StockControl.dto.product;

import com.edy.StockControl.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AuditingProduct(

        String sku,
        String name,
        BigDecimal price,
        Integer quantity,
        LocalDateTime created,
        LocalDateTime updated,
        BigDecimal calc
) {

    public static AuditingProduct fromEntity(Product product) {
        return new AuditingProduct(
                product.getSku(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCreated(),
                product.getUpdated(),
                product.getCalc()
        );
    }
}
