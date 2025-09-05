package com.acme.sales.aggregates;

import com.acme.sales.valueObjects.ProductId;
import com.acme.shared.domain.model.valueObject.CustomerId;
import com.acme.shared.domain.model.valueObject.Money;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a sales order in the system, including customer details, order date, total amount, and associated items.
 * This entity is part of the SalesOrder aggregate.
 * @author Open Source Application Development Team
 */
public class SalesOrder {

    @Getter
    private final UUID id;

    @Getter
    private final CustomerId customerId;

    @Getter
    private LocalDateTime orderDate;

    @Getter
    private Money totalAmount;

    // List of items in the sales order
    private final List<SalesOrderItem> items;

    /**
     * Constructs a SalesOrder with the specified customer ID.
     * Initializes the order date to the current date and time, total amount to zero, and generates a unique ID.
     *
     * @param customerId the unique identifier of the customer placing the order, must not be null
     */
    public SalesOrder(@NonNull CustomerId customerId) {
        this.totalAmount = Money.zero();
        this.orderDate = LocalDateTime.now();
        this.customerId = customerId;
        this.id = UUID.randomUUID();
        this.items = new ArrayList<>();
    }

    /**
     * Adds an item to the sales order with the specified product ID, quantity, and unit price.
     * Updates the total amount of the order accordingly.
     *
     * @param productId the unique identifier of the product being added, must not be null
     * @param quantity the quantity of the product being added, must be greater than zero
     * @param unitPrice the price per unit of the product, must be greater than zero and have a valid currency
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public void addItem(@NonNull ProductId productId, int quantity, @NonNull
    Money unitPrice) {
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero");
        if (unitPrice.amount().compareTo(Money.zero().amount()) <= 0)
            throw new IllegalArgumentException("Unit price must be greater than zero");

        SalesOrderItem newItem = new SalesOrderItem(productId, quantity, unitPrice);
        this.items.add(newItem);
        this.totalAmount = calculateTotalAmount();
    }

    /**
     * Calculates the total amount of the sales order by summing the total prices of all items.
     *
     * @return the total amount as a Money object
     */
    public Money calculateTotalAmount() {
        return this.items
                .stream()
                .map(SalesOrderItem::calculateTotalPrice)
                .reduce(Money.zero(), Money::add);
    }

    /**
     * Sets the order date of the sales order.
     *
     * @param orderDate the date and time when the order was placed, must not be null
     * @return the updated SalesOrder instance
     */
    public SalesOrder withOrderDate(@NonNull LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

   /**
     * Returns the total amount of the sales order as a formatted string.
     *
     * @return the total amount in the format "amount currencyCode"
     */
    public String getTotalAmountAsString() {
        return this.totalAmount.amount()
                + " "
                + this.totalAmount.currency().getCurrencyCode();
    }
}
