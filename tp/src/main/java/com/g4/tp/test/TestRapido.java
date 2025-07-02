package com.g4.tp.test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.g4.tp.model.entities.*;
import com.g4.tp.model.state.*;

public class TestRapido {

    public static void main(String[] args) {
        System.out.println("=== PRUEBA RÁPIDA DEL SISTEMA ===\n");

        try {
            // 1. Crear deporte
            Sport futbol = new Sport("Fútbol", "Deporte con pelota", 10);
            System.out.println("✓ Deporte creado: " + futbol.getName());

            // 2. Crear usuarios
            User usuario1 = new User("Juan", "juan@email.com", "123456");
            usuario1.setSkillLevel(SKILL_LEVEL_ENUM.INTERMEDIATE);

            User usuario2 = new User("Maria", "maria@email.com", "123456");
            usuario2.setSkillLevel(SKILL_LEVEL_ENUM.ADVANCED);

            User usuario3 = new User("Carlos", "carlos@email.com", "123456");
            usuario3.setSkillLevel(SKILL_LEVEL_ENUM.BEGINNER);

            System.out.println("✓ Usuarios creados: " + usuario1.getName() + ", " + usuario2.getName() + ", "
                    + usuario3.getName());

            // 3. Crear partido
            LocalDateTime fechaPartido = LocalDateTime.now().plusDays(1);
            Match partido = new Match(futbol, 90, fechaPartido, fechaPartido);

            // Crear contexto para manejar estados
            MatchContext contexto = new MatchContext(partido);

            System.out.println("✓ Partido creado - Estado inicial: " + contexto.getStateName());

            // 4. Probar transiciones de estado
            System.out.println("\n--- PROBANDO TRANSICIONES DE ESTADO ---");

            // Agregar algunos jugadores (pero no todos)
            for (int i = 0; i < 5; i++) {
                User jugador = new User("Jugador" + (i + 1), "jugador" + (i + 1) + "@email.com", "123456");
                contexto.joinUser(jugador);
                System.out.println("Jugador " + (i + 1) + " se unió. Jugadores actuales: " +
                        partido.getPlayers().size() + "/" + futbol.getRequiredPlayers());
            }

            System.out.println("Estado actual: " + contexto.getStateName());

            // Completar el equipo
            for (int i = 5; i < futbol.getRequiredPlayers(); i++) {
                User jugador = new User("Jugador" + (i + 1), "jugador" + (i + 1) + "@email.com", "123456");
                contexto.joinUser(jugador);
            }

            System.out.println("✓ Equipo completo - Estado: " + contexto.getStateName());
            System.out.println("Jugadores totales: " + partido.getPlayers().size());

            // 5. Confirmar partido
            contexto.confirmMatch();
            System.out.println("✓ Partido confirmado - Estado: " + contexto.getStateName());

            // 6. Simular inicio del partido
            contexto.updateProgress(10);
            System.out.println("✓ Partido iniciado - Estado: " + contexto.getStateName());

            // 7. Finalizar partido
            contexto.finishMatch();
            System.out.println("✓ Partido finalizado - Estado: " + contexto.getStateName());

            System.out.println("\n=== PRUEBA DE ERRORES ===");

            // 8. Probar casos de error
            try {
                contexto.joinUser(usuario1); // Debería fallar porque el partido ya terminó
            } catch (IllegalStateException e) {
                System.out.println("✓ Error capturado correctamente: " + e.getMessage());
            }

            // 9. Probar otro partido que se cancela
            System.out.println("\n--- PROBANDO CANCELACIÓN ---");
            Match partidoCancelado = new Match(futbol, 90, fechaPartido.plusDays(1), fechaPartido.plusDays(1));
            MatchContext contextoCancelado = new MatchContext(partidoCancelado);

            System.out.println("Partido creado - Estado: " + contextoCancelado.getStateName());
            contextoCancelado.cancel();
            System.out.println("✓ Partido cancelado - Estado: " + contextoCancelado.getStateName());

            System.out.println("\n=== TODAS LAS PRUEBAS COMPLETADAS EXITOSAMENTE ===");

        } catch (Exception e) {
            System.err.println("❌ Error durante la prueba: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método auxiliar para probar diferentes escenarios
    public static void probarEscenario(String nombre, Runnable test) {
        System.out.println("\n--- " + nombre + " ---");
        try {
            test.run();
            System.out.println("✓ " + nombre + " completado exitosamente");
        } catch (Exception e) {
            System.err.println("❌ " + nombre + " falló: " + e.getMessage());
        }
    }
}