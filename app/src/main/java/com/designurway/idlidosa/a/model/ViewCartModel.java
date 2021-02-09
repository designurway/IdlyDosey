package com.designurway.idlidosa.a.model;

public class ViewCartModel {
    private String product_name;
    private String image;
    private String product_id;
    private String quantity;
    private String order_id;
    private String price;
    private int qnt;
    private int amount;
    private String medicine_image;

    public ViewCartModel(String product_name, String image, String product_id, String quantity, String order_id, String price, int qnt, int amount, String medicine_image) {
        this.product_name = product_name;
        this.image = image;
        this.product_id = product_id;
        this.quantity = quantity;
        this.order_id = order_id;
        this.price = price;
        this.qnt = qnt;
        this.amount = amount;
        this.medicine_image = medicine_image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getMedicine_image() {
        return medicine_image;
    }

    public void setMedicine_image(String medicine_image) {
        this.medicine_image = medicine_image;
    }
}
