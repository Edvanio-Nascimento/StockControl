package com.edy.StockControl.dto.product;

import com.edy.StockControl.entity.Product;

import java.math.BigDecimal;

public record SummaryProduct(

        String sku,
        String name,
        BigDecimal price
) {

    public static SummaryProduct fromEntity(Product product) {
        return new SummaryProduct(
                product.getSku(),
                product.getName(),
                product.getPrice()
        );
    }
}
