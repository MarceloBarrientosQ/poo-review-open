package com.acme.shared.domain.model.valueObject;

import java.util.Objects;

public record Address(String street, String city, String postalCode, String country) {

    /**
     * Constructs an Address with the specified street, city, postal code, and country.
     *
     * @param street     the street address, must be non-null and non-blank
     * @param city       the city name, must be non-null and non-blank
     * @param postalCode the postal code, must be non-null and non-blank
     * @param country    the country name, must be non-null and non-blank
     * @throws IllegalArgumentException if any of the parameters are null or blank
     */
    public Address {
        if(street == null || street.isBlank())
            throw new IllegalArgumentException("Street cannot be null or blank");
        if(Objects.isNull(city) || city.isBlank())
            throw new IllegalArgumentException("City cannot be null or blank");
        if(Objects.isNull(postalCode) || postalCode.isBlank())
            throw new IllegalArgumentException("Postal code cannot be null or blank");
        if(Objects.isNull(country) || country.isBlank())
            throw new IllegalArgumentException("Country cannot be null or blank");
    }

    /**
     * Returns the string representation of the Address.
     *
     * @return a formatted string containing street, city, postal code, and country
     */
    public String toString() {
        return String.format("%s %s %s %s", street, city, postalCode, country);
    }
}
