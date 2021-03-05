package com.designurway.idlidosa.model;

public class NotificationListData {
    private String customer_id;
    private String order_id;
    private String title;
    private String message;
    private String delivery_boy_id;
    private String created_date;
    private String notification_status;

    public String getNotification_status() {
        return notification_status;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getDelivery_boy_id() {
        return delivery_boy_id;
    }
}
