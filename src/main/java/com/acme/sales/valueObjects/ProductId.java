package com.acme.sales.valueObjects;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a unique identifier for a product within sales bounded context
 * @param value
 *
 * @author Open Source Application Development Team
 * @version 1.0
 */

public record ProductId(UUID value) {

    /**
     * Construct a new ProductId with the given UUID value.
     * @param value the product identifier value, it must not be null
     * @throws IllegalArgumentException if the value is null.
     */

    public ProductId {
        if (Objects.isNull(value))
            throw new IllegalArgumentException("ProductId value cannot be null");
    }

    /**
     * Construct a new ProductId with a random UUID value.
     */
    public ProductId() {
        this(UUID.randomUUID());
    }
}
