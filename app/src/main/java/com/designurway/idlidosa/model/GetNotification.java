package com.designurway.idlidosa.model;

public class GetNotification {

    private final String message_id;

    public GetNotification(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage_id() {
        return message_id;
    }
}
