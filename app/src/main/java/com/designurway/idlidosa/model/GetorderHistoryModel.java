package com.designurway.idlidosa.model;

public class GetorderHistoryModel {
    private String product_name;
    private String order_id;
    private String price;
    private String created_date;
    private String order_status;
    private String cutomer_rating;

    public String getOrder_id() {
        return order_id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getPrice() {
        return price;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getCutomer_rating() {
        return cutomer_rating;
    }
}
