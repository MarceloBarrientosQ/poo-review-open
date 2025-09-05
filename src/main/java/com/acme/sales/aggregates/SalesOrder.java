package com.acme.sales.aggregates;

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
     * @param customerId the unique identifier of the customer placing the order, must not be null
     */
    public SalesOrder(@NonNull CustomerId customerId) {
        this.totalAmount = Money.zero();
        this.orderDate =  LocalDateTime.now();
        this.customerId = customerId;
        this.id = UUID.randomUUID();
        this.items = new ArrayList<>();
    }


}
