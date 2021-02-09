package com.designurway.idlidosa.a.model;

import java.util.ArrayList;

public class ReceivedComboDataModel {
    private String status;
    private String message;
    private ArrayList<ReceivedComboModel> data;

    public ReceivedComboDataModel(String status, String message, ArrayList<ReceivedComboModel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
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

    public ArrayList<ReceivedComboModel> getData() {
        return data;
    }

    public void setData(ArrayList<ReceivedComboModel> data) {
        this.data = data;
    }
}
