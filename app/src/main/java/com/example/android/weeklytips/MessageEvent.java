package com.example.android.weeklytips;

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    @SuppressWarnings("WeakerAccess")
    public String getMessage() {
        return message;
    }
}
