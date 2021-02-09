package com.designurway.idlidosa.model;

import java.util.ArrayList;

public class GetOrderHistoryResponseModel {

    private String status;
    private String message;
    private ArrayList<GetorderHistoryModel> data;

    public GetOrderHistoryResponseModel(String status, String message, ArrayList<GetorderHistoryModel> data) {
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

    public ArrayList<GetorderHistoryModel> getData() {
        return data;
    }

    public void setData(ArrayList<GetorderHistoryModel> data) {
        this.data = data;
    }
}
