package com.designurway.idlidosa.a.model;

public class StatusOTPModel {
    private String status;
    private String otp;

    public StatusOTPModel(String status, String otp) {
        this.status = status;
        this.otp = otp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
