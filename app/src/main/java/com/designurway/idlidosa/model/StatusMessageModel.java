package com.designurway.idlidosa.model;

import com.google.gson.annotations.SerializedName;

public class StatusMessageModel {
    private String status;
    private String message;
    @SerializedName("profile_image")
    private String image;

    public StatusMessageModel(String status, String message, String image) {
        this.status = status;
        this.message = message;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
