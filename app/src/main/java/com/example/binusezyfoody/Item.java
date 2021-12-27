package com.example.binusezyfoody;

import java.io.Serializable;

public class Item implements Serializable {

    public String name;
    public String category;
    public int price;
    public int image;

    public Item(String name, String category, int price, int image){
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
    }
}
