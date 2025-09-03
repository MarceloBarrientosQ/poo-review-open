package com.acme.shared.domain.model.valueObject;

import java.util.Objects;
import java.util.UUID;

public record CustomerId(UUID value) {

    /**
     * Constructs a CustomerId with the given UUID value.
     * @param value the UUID value for the CustomerId
     * @throws IllegalArgumentException if the value is null
     */
    public CustomerId {
        if(Objects.isNull(value)) {
            throw new IllegalArgumentException("CustomerId value cannot be null");
        }
    }

    /**
     * Generates a new CustomerId with a random UUID.
     */
    public CustomerId() {
        this(UUID.randomUUID());
    }

    /**
     * Returns the string representation of the CustomerId.
     * @return the string representation of the UUID value
     */
    public String toString() {
        return value.toString();
    }
}
