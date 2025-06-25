package com.g4.tp.model.adapter;

import com.g4.tp.model.entities.User;

public class AdapterEmailNotification {

    public void enviarEmail(String recipient, User user, String message) {
        // TODO: Adaptar la interfaz de JavaMail al sistema
        // TODO: Implementar lógica específica de JavaMail
        System.out.println("Adapter: Sending email via JavaMail");
        System.out.println("To: " + recipient);
        System.out.println("User: " + user.getName());
        System.out.println("Message: " + message);
    }

}