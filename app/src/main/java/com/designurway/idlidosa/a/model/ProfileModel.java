package com.designurway.idlidosa.a.model;

import com.google.gson.annotations.SerializedName;

public class ProfileModel {
    private String name;

    private String email;

    private String phone;

    private String password;
    @SerializedName("customer_id")
    private String id;

    @SerializedName("referral_code")
    private String referralCode;

    @SerializedName("referred_from")
    private String referredFrom;

    @SerializedName("home_address")
    private String homeAddress;

    @SerializedName("profile_image")
    private String profileImage;
    private String pin_code;


    public ProfileModel(String name, String email, String phone, String password, String id, String referralCode, String referredFrom, String homeAddress, String profileImage, String pin_code) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.id = id;
        this.referralCode = referralCode;
        this.referredFrom = referredFrom;
        this.homeAddress = homeAddress;
        this.profileImage = profileImage;
        this.pin_code = pin_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }
}
