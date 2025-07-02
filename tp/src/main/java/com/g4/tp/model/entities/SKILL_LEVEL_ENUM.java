package com.g4.tp.model.entities;

public enum SKILL_LEVEL_ENUM {
    BEGINNER("Principiante"),
    INTERMEDIATE("Intermedio"),
    ADVANCED("Avanzado");

    private final String displayName;

    SKILL_LEVEL_ENUM(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}