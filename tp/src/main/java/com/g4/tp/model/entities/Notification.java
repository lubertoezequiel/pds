package com.g4.tp.model.entities;

public abstract class Notification {

    public abstract void sendNotification(String recipient, User user, String message);

}