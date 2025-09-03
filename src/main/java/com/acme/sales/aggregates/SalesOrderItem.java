package com.acme.sales.aggregates;

import com.acme.sales.valueObjects.ProductId;
import com.acme.shared.domain.model.valueObject.Money;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;


/**
 * Represents an item in a sales order, including product details, quantity, and pricing.
 * This entity is part of the SalesOrder aggregate.
 * @author Open Source Application Development Team
 */
@Getter
public class SalesOrderItem {

    private final ProductId productId;
    @Setter private int quantity;
    @Setter private Money unitPrice;

    /**
     * Constructs a SalesOrderItem with the specified product ID, quantity, and unit price.
     * @param productId the unique identifier of the product, must not be null
     * @param quantity the quantity of the product ordered, must be greater than zero
     * @param unitPrice the price per unit of the product, must be greater than zero and have a valid currency
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public SalesOrderItem(@NonNull ProductId productId, int quantity, @NonNull Money unitPrice) {
        if(quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");
        if(unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Unit price must be greater than zero");
        if(Objects.isNull(unitPrice.currency()) || Objects.isNull(unitPrice.currency().getCurrencyCode()) ||
                unitPrice.currency().getCurrencyCode().isBlank())
            throw new IllegalArgumentException("Unit price currency must be valid");

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the total price for this sales order item by multiplying the unit price by the quantity.
     * @return the total price as a Money object
     */
    public Money calculateTotalPrice() {
        return unitPrice.multiply(quantity);
    }
}
