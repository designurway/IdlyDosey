package com.designurway.idlidosa.a.model;

import java.util.List;

public class NotificationListModel {
    private String status;
    private String message;
    private List<NotificationListData> data;


    public NotificationListModel(String status, String message, List<NotificationListData> data) {
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

    public List<NotificationListData> getData() {
        return data;
    }

    public void setData(List<NotificationListData> data) {
        this.data = data;
    }
}
