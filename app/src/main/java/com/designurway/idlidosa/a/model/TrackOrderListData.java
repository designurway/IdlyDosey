package com.designurway.idlidosa.a.model;

public class TrackOrderListData {
    private String order_id;
    private String amount;


    public TrackOrderListData(String order_id, String amount) {
        this.order_id = order_id;
        this.amount = amount;

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


}
