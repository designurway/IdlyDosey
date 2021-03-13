package com.designurway.idlidosa.a.model;

public class OrderStatusModel {
    private String error;
    private String message;
    private String order_id;

    public String getOrder_id() {
        return order_id;
    }

    public OrderStatusModel(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
