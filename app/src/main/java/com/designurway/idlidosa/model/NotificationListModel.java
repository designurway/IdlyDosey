package com.designurway.idlidosa.model;

import java.util.ArrayList;
import java.util.List;

public class NotificationListModel {
    private String status;
    private String message;
    private ArrayList<NotificationListData> data;


    public NotificationListModel(String status, String message, ArrayList<NotificationListData> data) {
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

    public ArrayList<NotificationListData> getData() {
        return data;
    }

    public void setData(ArrayList<NotificationListData> data) {
        this.data = data;
    }
}
