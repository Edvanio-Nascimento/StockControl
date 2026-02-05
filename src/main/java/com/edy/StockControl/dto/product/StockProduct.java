package com.edy.StockControl.dto.product;

import com.edy.StockControl.entity.Product;
import com.edy.StockControl.interfaces.ProductViewInterface;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockProduct(

        String sku,
        String name,
        Integer quantity

) implements ProductViewInterface {

    public static StockProduct fromEntity(Product product) {
        return new StockProduct(
                product.getSku(),
                product.getName(),
                product.getQuantity()
        );
    }
}
