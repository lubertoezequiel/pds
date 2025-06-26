package com.g4.tp.model.adapter;

import com.g4.tp.model.entities.User;

public class AdapterJavaMail {

    public void enviarEmail(String recipient, User user, String message) {
        // TODO: Implementar integración real con JavaMail
        // Esta clase simula la librería externa JavaMail
        System.out.println("JavaMail Library: Sending email...");
        System.out.println("Recipient: " + recipient);
        System.out.println("From User: " + user.getEmail());
        System.out.println("Content: " + message);
        System.out.println("Email sent successfully!");
    }

}