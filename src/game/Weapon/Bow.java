package game.Weapon;
import game.MeleeWeapon;

public class Bow extends MeleeWeapon {


    private String bowPassiveSkill;


    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public Bow(String name, char displayChar, int damage, String verb, int hitRate, String swordPassiveSkill) {
        super(name, displayChar, damage, verb, hitRate);
        this.bowPassiveSkill = bowPassiveSkill;
    }

    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public Bow(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    /**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     */
    public int criticalStrike() {
        int i = (int) (Math.random() * 5);
        if (i == 1) {
            int criticalDamage = damage * 2;
            return criticalDamage;
        }else
            return super.damage();
    }
}
