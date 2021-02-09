package com.designurway.idlidosa.a.model;

public class Menumodel {
    private  String product_id;
    private  String product_name;
    private  String price;
    private  String description;
    private  String product_present;
    private  String image;
    private String  order_qty;
    private int qty=0;


    public String getProduct_present() {
        return product_present;
    }

    public void setProduct_present(String product_present) {
        this.product_present = product_present;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getOrder_qty() {
        return order_qty;
    }

    public void setOrder_qty(String order_qty) {
        this.order_qty = order_qty;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }
}
