package com.designurway.idlidosa.model;

public class ViewCartModel {
    private String product_name;
    private String image;
    private String product_id;
    private String quantity;
    private String order_id;
    private String price;
    private int qnt;
    private int amount;

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getImage() {
        return image;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getOrder_id() {
        return order_id;
    }
}
