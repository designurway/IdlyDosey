package com.designurway.idlidosa.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {
    @SerializedName("id")
    private String customerId;

    private String name;

    @SerializedName("email")
    private String emailId;

    private String phone;

    private String message;

    private String password;

    @SerializedName("referral_code")
    private String referralCode;

    @SerializedName("referred_from")
    private String referredFrom;


    public LoginModel(String customerId, String name, String emailId,
                      String phone, String message, String password, String referralCode, String referredFrom) {
        this.customerId = customerId;
        this.name = name;
        this.emailId = emailId;
        this.phone = phone;
        this.message = message;
        this.password = password;
        this.referralCode = referralCode;
        this.referredFrom = referredFrom;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getReferredFrom() {
        return referredFrom;
    }

    public void setReferredFrom(String referredFrom) {
        this.referredFrom = referredFrom;
    }
}
