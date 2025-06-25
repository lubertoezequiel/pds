package com.g4.tp.model.entities;

public class EmailNotification extends Notification {

    private String adapter; // Para patrón Adapter

    public EmailNotification() {
        this.adapter = "AdapterEmailNotification";
    }

    @Override
    public void sendNotification(String recipient, User user, String message) {
        // TODO: Implementar envío de email via JavaMail
        System.out.println("Sending email to: " + recipient);
        System.out.println("Message: " + message);
    }

    public String getAdapter() {
        return adapter;
    }

    public void setAdapter(String adapter) {
        this.adapter = adapter;
    }
}