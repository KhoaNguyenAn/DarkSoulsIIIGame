package game.enums;

/**
 * Enum that represents an ability of Actor, Item, or Ground.
 */
public enum Abilities {
    REST,
    BUY_ITEMS,
    CHARGE,
    SPIN_ATTACK,
    FALL,   // To fall from valley
    ENTER,  // To enter the floor
    REVIVE, // To revive skeleton or player
    BOSS,    // To distinguish it from normal enemies
    YHORM,    // To achieve unique feature
    PLAYER,
    RANGE_ATTACK,// To display messages after player dead
}
