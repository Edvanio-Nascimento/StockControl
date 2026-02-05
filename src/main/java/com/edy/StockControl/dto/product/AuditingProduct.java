package com.edy.StockControl.dto.product;

import com.edy.StockControl.entity.Product;
import com.edy.StockControl.interfaces.ProductViewInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AuditingProduct(

        String sku,
        String name,
        LocalDateTime created,
        LocalDateTime updated

) implements ProductViewInterface {

    public static AuditingProduct fromEntity(Product product) {
        return new AuditingProduct(
                product.getSku(),
                product.getName(),
                product.getCreated(),
                product.getUpdated()
        );
    }
}
