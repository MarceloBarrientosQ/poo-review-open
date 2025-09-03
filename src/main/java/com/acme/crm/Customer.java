package com.acme.crm;

import com.acme.shared.domain.model.valueObject.Address;
import com.acme.shared.domain.model.valueObject.CustomerId;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

/**
 * Represents a customer in the CRM system.
 * A Customer has a unique CustomerId, name, email, and address.
 * The class provides methods to update contact information and retrieve contact details.
 */
@Getter
public class Customer {
    private final CustomerId customerId;
    @Setter @NonNull private String name;
    @Setter @NonNull private String email;
    @Setter @NonNull private Address address;

    /**
     * Constructs a Customer with the specified name, email, and address.
     *
     * @param name    the customer's name, must be non-null and non-blank
     * @param email   the customer's email, must be non-null and non-blank
     * @param address the customer's address, must be non-null
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public Customer(String name, String email, Address address) {
        if(Objects.isNull(address))
            throw new IllegalArgumentException("Address cannot be null");
        if(Objects.isNull(email) || email.isBlank())
            throw new IllegalArgumentException("Email cannot be null or blank");
        if(Objects.isNull(name) || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or blank");

        this.customerId = new CustomerId();
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Updates the customer's contact information with the specified email and address.
     *
     * @param email   the new email, must be non-null and non-blank
     * @param address the new address, must be non-null
     * @throws IllegalArgumentException if any of the parameters are invalid
     */
    public void updateContactInfo(@NonNull String email,
                                  @NonNull Address address) {
        if(email.isBlank())
            throw new IllegalArgumentException("Email cannot be blank");
        this.email = email;
        this.address = address;
    }

    /**
     * Returns the customer's contact information as a formatted string.
     *
     * @return a string containing the customer's name, email, and address
     */
    public String getContactInfo() {
        return String.format("Name: %s, Email: %s, Address: %s", name, email, address);
    }

}
