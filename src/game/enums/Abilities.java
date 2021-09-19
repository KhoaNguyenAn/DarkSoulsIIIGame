package game.enums;

/**
 * Enum that represents an ability of Actor, Item, or Ground.
 */
public enum Abilities {
    REST,
    FALL,   // To fall from valley
    ENTER,  // To enter the floor
    REVIVE, // To revive skeleton
    BOSS, 	// To display messages after boss is defeated could be used for weak to storm ruler
    PLAYER, // To display messages after player dead
}
