package com.example.delivery_food;

public class Product_model {

    int img, id;
    String name, cost;

    public Product_model(int img, String name, String cost, int id){

        this.id = id;
        this.img = img;
        this.name = name;
        this.cost = cost;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int img) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
