package com.designurway.idlidosa.model;

public class NotificationListData {
    private String title;
    private String message;
    private String image;
    private String created_date;
    private String id;

    public NotificationListData(String title, String message, String image, String created_date, String id) {
        this.title = title;
        this.message = message;
        this.image = image;
        this.created_date = created_date;
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
