package com.edy.StockControl.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProductViewEnum {

    DETAILS,
    AUDIT,
    STOCK;

    @JsonCreator
    public static ProductViewEnum from(String value) {
        return valueOf(value.toUpperCase());
    }
}
