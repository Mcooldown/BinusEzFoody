package com.example.binusezyfoody;

import java.io.Serializable;

public class OrderItem implements Serializable {
    public Item item;
    public int quantity;

    public OrderItem(Item item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }
}
