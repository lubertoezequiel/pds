package com.g4.tp.model.strategy;
import org.springframework.stereotype.Component;

/**
 * Factory para crear instancias de estrategias de matching
 * Implementa el patrón Factory para centralizar la creación de estrategias
 */
@Component
public class StrategyFactory {

    /**
     * Enum que define los tipos de estrategias disponibles
     */
    public enum StrategyType {
        PROXIMITY("Proximity"),
        SKILL_LEVEL("SkillLevel");

        private final String name;

        StrategyType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Crea una instancia de la estrategia especificada
     * 
     * @param strategyType Tipo de estrategia a crear
     * @return Instancia de la estrategia solicitada
     * @throws IllegalArgumentException si el tipo de estrategia no es válido
     */
    public static IMatchingStrategy createStrategy(StrategyType strategyType) {
        switch (strategyType) {
            case PROXIMITY:
                return new ProximityMatching();
            case SKILL_LEVEL:
                return new SkillLevelMatching();
            default:
                throw new IllegalArgumentException("Tipo de estrategia no válido: " + strategyType);
        }
    }


    public static IMatchingStrategy createStrategy(String strategyName) {
        if (strategyName == null || strategyName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la estrategia no puede ser nulo o vacío");
        }

        switch (strategyName.toLowerCase()) {
            case "proximity":
                return new ProximityMatching();
            case "skilllevel":
            case "skill_level":
                return new SkillLevelMatching();
            default:
                throw new IllegalArgumentException("Estrategia no reconocida: " + strategyName);
        }
    }


    public static StrategyType getStrategyType(IMatchingStrategy strategy) {
        if (strategy instanceof ProximityMatching) {
            return StrategyType.PROXIMITY;
        } else if (strategy instanceof SkillLevelMatching) {
            return StrategyType.SKILL_LEVEL;
        } else {
            throw new IllegalArgumentException("Estrategia no reconocida: " + strategy.getClass().getSimpleName());
        }
    }


    public static boolean isValidStrategyName(String strategyName) {
        if (strategyName == null || strategyName.trim().isEmpty()) {
            return false;
        }

        try {
            createStrategy(strategyName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }


    public static StrategyType[] getAvailableStrategies() {
        return StrategyType.values();
    }

 
    public static ProximityMatching createProximityStrategy(double maxDistance) {
        ProximityMatching strategy = new ProximityMatching();
        strategy.setMaxDistance(maxDistance);
        return strategy;
    }


    public static SkillLevelMatching createSkillLevelStrategy(
            com.g4.tp.model.entities.SKILL_LEVEL_ENUM minLevel,
            com.g4.tp.model.entities.SKILL_LEVEL_ENUM maxLevel,
            boolean enableBalancing) {
        SkillLevelMatching strategy = new SkillLevelMatching();
        strategy.setMinSkillLevel(minLevel);
        strategy.setMaxSkillLevel(maxLevel);
        strategy.setEnableBalancing(enableBalancing);
        return strategy;
    }
}