package com.acme.shared.domain.model.valueObject;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public record Money(BigDecimal amount, Currency currency) {

    /**
     * Constructs a Money instance with the specified amount and currency.
     *
     * @param amount   the monetary amount, must be non-null and non-negative
     * @param currency the currency of the amount, must be non-null
     * @throws IllegalArgumentException if amount is null or negative, or if currency is null,
     *                                  or if amount's scale exceeds currency's default fraction digits
     */
    public Money {
        if(Objects.isNull(amount) || amount.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Ammout cannot be null or negative");
        if(Objects.isNull(currency))
            throw new IllegalArgumentException("Currency cannot be null");
        if(amount.scale() > currency.getDefaultFractionDigits())
            throw new IllegalArgumentException("Ammout scale cannot be greater than currency default fraction digits");
    }

    /**
     * Creates a Money instance representing zero amount in USD currency.
     *
     * @return a Money instance with zero amount and USD currency
     */
    public static Money zero() {
        return new Money(BigDecimal.ZERO, Currency.getInstance("USD"));
    }

    /**
     * Adds another Money instance to this one, ensuring both have the same currency.
     *
     * @param other the Money instance to add
     * @return a new Money instance with the summed amount and the same currency
     * @throws IllegalArgumentException if the currencies of the two Money instances do not match
     */
    public Money add(Money other) {
        if(!this.currency.equals(other.currency))
            throw new IllegalArgumentException("Cannot add Money with different currencies");
        return new Money(this.amount.add(other.amount), this.currency);
    }

    /**
     * Multiplies the monetary amount by a given integer multiplier.
     *
     * @param multiplier the integer value to multiply the amount by
     * @return a new Money instance with the multiplied amount and the same currency
     * @throws IllegalArgumentException if the multiplier is negative
     */
    public Money multiply(int multiplier) {
        return new Money(amount.multiply(BigDecimal.valueOf(multiplier)), this.currency);
    }
}
