package com.designurway.idlidosa.model;

import com.google.gson.annotations.SerializedName;

public class RegisterDataModel {

        private String status;
        private String message;
        @SerializedName("id")
        private String customer_id;
        private String email;
        private String name;
        private String phone;
        private String password;
        private String referral_code;
        private String referred_from;

    public RegisterDataModel(String status, String message, String customer_id, String email,
                             String name, String phone, String password, String referral_code, String referred_from) {
        this.status = status;
        this.message = message;
        this.customer_id = customer_id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.referral_code = referral_code;
        this.referred_from = referred_from;
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

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReferral_code() {
        return referral_code;
    }

    public void setReferral_code(String referral_code) {
        this.referral_code = referral_code;
    }

    public String getReferred_from() {
        return referred_from;
    }

    public void setReferred_from(String referred_from) {
        this.referred_from = referred_from;
    }
}


