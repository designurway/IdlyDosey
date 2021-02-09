package com.designurway.idlidosa.a.model;

public class ProfileDataModel {
    private String status;
    private ProfileModel data;

    public ProfileDataModel(String status, ProfileModel data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProfileModel getData() {
        return data;
    }

    public void setData(ProfileModel data) {
        this.data = data;
    }
}
