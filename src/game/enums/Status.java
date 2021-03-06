package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {

    HOSTILE_TO_ENEMY,		// use this capability to be hostile towards something (e.g., to be attacked by enemy)
    SOFTRESET,		// use this status to achieve soft reset feature
    ACTIVE,		// use this status to display active message for boss
    STUNNED,
    EMBER_FORM,
    OPENED,		// the status to illustrate if the chest is opened or not
    VENDOR  // to indicate the actor is vendor
}


