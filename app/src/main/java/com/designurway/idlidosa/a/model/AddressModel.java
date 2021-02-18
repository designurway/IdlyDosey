package com.designurway.idlidosa.a.model;

import com.google.gson.annotations.SerializedName;

public class AddressModel {
    private String status;

    private String name;

    private String phone;
    private String pin_code;
    private String office_pin_code;

    @SerializedName("home_address")
    private String homeAddress;

    @SerializedName("other_address")
    private String otherAddress;


    public AddressModel(String status, String name, String phone, String pin_code, String office_pin_code, String homeAddress, String otherAddress) {
        this.status = status;
        this.name = name;
        this.phone = phone;
        this.pin_code = pin_code;
        this.office_pin_code = office_pin_code;
        this.homeAddress = homeAddress;
        this.otherAddress = otherAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

    public String getOffice_pin_code() {
        return office_pin_code;
    }

    public void setOffice_pin_code(String office_pin_code) {
        this.office_pin_code = office_pin_code;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getOtherAddress() {
        return otherAddress;
    }

    public void setOtherAddress(String otherAddress) {
        this.otherAddress = otherAddress;
    }
}
