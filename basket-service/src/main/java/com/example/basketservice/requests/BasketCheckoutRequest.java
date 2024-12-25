package com.example.basketservice.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketCheckoutRequest {
    private String userName;
    private double totalPrice;
    // BillingAddress
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String addressLine;
    private String country;
    private String state;
    private String zipCode;
    // Payment
    private String cardName;
    private String cardNumber;
    private String expiration;
    private String cvv;
    private int paymentMethod;

}
