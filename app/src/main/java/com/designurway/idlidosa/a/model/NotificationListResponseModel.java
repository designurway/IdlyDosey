package com.designurway.idlidosa.a.model;

public class NotificationListResponseModel {
    private String total_amount;
    private String delivery_address;
    private String phone;
    private String created_date;
    private String order_id;
    private String customer_id;
    private String order_status;

    public String getNotification_status() {
        return notification_status;
    }

    private String notification_status;

    public String getOrder_status() {
        return order_status;
    }

    public String getTotal_amount() {
        return total_amount;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCreated_date() {
        return created_date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }
}
