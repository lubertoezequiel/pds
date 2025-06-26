package com.g4.tp.model.entities;

import com.g4.tp.model.observer.Observable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifiers")
public class Notifier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String message;
    private String recipient;

    public Notifier() {
    }

    public Notifier(String message, String recipient) {
        this.message = message;
        this.recipient = recipient;
    }

    public void sendNotification(User user, String message) {
        // TODO: Implementar lógica de envío de notificaciones
        // TODO: Usar patrón Strategy para diferentes tipos de notificación
    }

    public void setStrategy(Notification strategy) {
        // TODO: Implementar patrón Strategy para notificaciones
    }

    public void updateObservable(Observable observable) {
        // TODO: Implementar patrón Observer
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
