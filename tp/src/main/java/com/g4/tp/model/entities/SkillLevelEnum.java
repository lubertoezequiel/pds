package com.g4.tp.model.entities;

public enum SkillLevelEnum {
    BEGINNER("Beginner"),
    INTERMEDIATE("Intermediate"),
    ADVANCED("Advanced");

    private final String level;

    SkillLevelEnum(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
}