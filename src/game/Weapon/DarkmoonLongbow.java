package game.Weapon;


public class DarkmoonLongbow  extends Bow{


    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public DarkmoonLongbow(String name, char displayChar, int damage, String verb, int hitRate, String swordPassiveSkill) {
        super("DarkmoonLongbow", ')',70, "shoot", 80,"critical Strike");




    }
    public DarkmoonLongbow(){
        super("DarkmoonLongbow", ')',70, "shoot", 80,"critical Strike");

    }

    /**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     */
    @Override
    public int damage() {
        int newDamage=criticalStrike();
        return newDamage;
    }
}
