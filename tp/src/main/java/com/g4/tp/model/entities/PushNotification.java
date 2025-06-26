package com.g4.tp.model.entities;

public class PushNotification extends Notification {

    private String adapter; // Para patrón Adapter

    public PushNotification() {
        this.adapter = "AdapterPushNotification";
    }

    @Override
    public void sendNotification(String recipient, User user, String message) {
        // TODO: Implementar envío de push notification via Firebase
        System.out.println("Sending push notification to: " + recipient);
        System.out.println("Message: " + message);
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }
}
