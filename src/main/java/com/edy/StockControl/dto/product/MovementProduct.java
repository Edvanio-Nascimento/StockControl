package com.edy.StockControl.dto.product;

import jakarta.validation.constraints.Min;

public record MovementProduct(

        @Min(1) int quantity
) {
}
