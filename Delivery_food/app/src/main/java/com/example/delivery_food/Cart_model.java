package com.example.delivery_food;

import java.util.ArrayList;

public class Cart_model {
    int img, id;
    String name, cost;

    public Cart_model(int img, String name, String cost, int id) {

        this.img = img;
        this.name = name;
        this.cost = cost;
        this.id = id;
    }

    public static ArrayList<Cart_model> items_product = new ArrayList<>();
    public static ArrayList<String> items_cost = new ArrayList<>();
    public static Boolean isPlus;
}
