package com.farmtech.testepratico_backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProductStatus {
    ACTIVE,
    INACTIVE,
    DISCONTINUED,
    IN_TESTING;

    // JSON passa a aceitar variações de maiúsculas e minúsculas
    @JsonCreator
    public static ProductStatus fromString(String value) {
        if (value == null) {
            return null;
        }
        //apenas converte para maiúsculo e busca o Enum
        return ProductStatus.valueOf(value.toUpperCase());
    }
}