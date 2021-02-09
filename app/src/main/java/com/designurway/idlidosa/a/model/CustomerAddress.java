package com.designurway.idlidosa.a.model;

import org.parceler.Parcel;

@Parcel
public class CustomerAddress {

    String name;
    String cityAddress;
    String amount;

    public CustomerAddress(String name, String cityAddress, String amount, String mobile) {
        this.name = name;
        this.cityAddress = cityAddress;
        this.amount = amount;
        this.mobile = mobile;
    }

    String mobile;

    public CustomerAddress() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityAddress() {
        return cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
