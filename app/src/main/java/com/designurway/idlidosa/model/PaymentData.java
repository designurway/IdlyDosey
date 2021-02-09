package com.designurway.idlidosa.model;

public class PaymentData {
    private String total_amount;

    public PaymentData(String total_amount) {
        this.total_amount = total_amount;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }
}
