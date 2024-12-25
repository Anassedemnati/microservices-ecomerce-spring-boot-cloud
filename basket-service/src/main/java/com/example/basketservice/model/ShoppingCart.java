package com.example.basketservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ShoppingCart implements Serializable {
    private String userName;
    private List<BasketItem> items;
}