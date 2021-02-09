package com.designurway.idlidosa.a.model;

public class PaymentSucessfulModel {

    private String status;
    private String message;

    public PaymentSucessfulModel(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
