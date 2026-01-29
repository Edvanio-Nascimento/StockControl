package com.edy.StockControl.dto.product;

import com.edy.StockControl.entity.Product;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProduct(

        @NotBlank(message = "SKU is required")
        @Size(max = 20, message = "SKU must be at most 20 characters")
        String sku,

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name must be at most 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 255, message = "Description must be at most 255 characters")
        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
        BigDecimal price
) {

    public Product toEntity() {
        Product product = new Product();

        product.setSku(this.sku);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setPrice(this.price);

        return product;
    }
}
