package com.designurway.idlidosa.model;

public class OrderHistoryModel {
    private String orderId;
    private String amount;
    private String orderedDate;
    private String orderStatus;

    public OrderHistoryModel(String orderId, String amount, String orderedDate,String orderStatus) {
        this.orderId = orderId;
        this.amount = amount;
        this.orderedDate = orderedDate;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(String orderedDate) {
        this.orderedDate = orderedDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
