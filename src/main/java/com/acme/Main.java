package com.acme;


import com.acme.crm.Customer;
import com.acme.sales.aggregates.SalesOrder;
import com.acme.sales.valueObjects.ProductId;
import com.acme.shared.domain.model.valueObject.Address;
import com.acme.shared.domain.model.valueObject.Money;

import java.math.BigDecimal;
import java.util.Currency;

public class Main {
    public static void main(String[] args) {
       // Shared Content
        Address address = new Address("Av. General Salaverry UPC",
                "Lima",
                "1234556",  "PERU");

        Address anotherAddress = new Address
                ("Av. General Primavera UPC",
                "Lima",
                "1234556",  "PERU");

        System.out.println( "First Address" + address);
        System.out.println( "Second Address" + anotherAddress);

        // CRM context
        System.out.println( "Creating a customer.....");

        Customer customer = new Customer("Juan Perez", "u20221a333@upc.edu.pe", address);
        System.out.println("Customer Contact Info: " + customer.getContactInfo());
        System.out.println("Updating Customer Contact Info.....");
        customer.updateContactInfo(customer.getEmail(), anotherAddress);
        System.out.println("Customer Contact Info: " + customer.getContactInfo());

        // Sales Order context
        System.out.println("Creating a sales order.....");
        SalesOrder salesOrder = new SalesOrder(customer.getCustomerId());

        Money price = new Money(new BigDecimal
                ("29.99"), Currency.getInstance("USD"));

        ProductId productId = new ProductId();

        salesOrder.addItem(productId, 2, price);

        System.out.println("Sales Order Id" + salesOrder.getId());
        System.out.println("Order Data" + salesOrder.getOrderDate());
        System.out.println("Customer Id" + salesOrder.getCustomerId());
        System.out.println("Total Amount" + salesOrder.getTotalAmountAsString());


    }
}