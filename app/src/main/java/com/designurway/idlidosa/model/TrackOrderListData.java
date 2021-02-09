package com.designurway.idlidosa.model;

public class TrackOrderListData {
    private String order_id;
    private String amount;
    private String image;

    public TrackOrderListData(String order_id, String amount, String image) {
        this.order_id = order_id;
        this.amount = amount;
        this.image = image;
    }


    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
