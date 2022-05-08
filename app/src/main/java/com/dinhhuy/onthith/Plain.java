package com.dinhhuy.onthith;

public class Plain {
    private int id, quantity;
    private String name;
    private int resouurceImg;
    private Double price;

    public Plain(int id, int quantity, String name, int resouurceImg, Double price) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.resouurceImg = resouurceImg;
        this.price = price;
    }
    public Plain(int id, String name, int resouurceImg, Double price) {
        this.id = id;
        this.name = name;
        this.resouurceImg = resouurceImg;
        this.price = price;
    }
    public  Plain(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResouurceImg() {
        return resouurceImg;
    }

    public void setResouurceImg(int resouurceImg) {
        this.resouurceImg = resouurceImg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
