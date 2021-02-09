package com.designurway.idlidosa.a.model;

import com.google.gson.annotations.SerializedName;

public class AddressModel {
    private String status;

    private String name;

    private String phone;

    @SerializedName("home_address")
    private String homeAddress;

    @SerializedName("other_address")
    private String otherAddress;

    public AddressModel(String status, String name, String homeAddress, String otherAddress,String phone) {
        this.status = status;
        this.name = name;
        this.homeAddress = homeAddress;
        this.otherAddress = otherAddress;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
