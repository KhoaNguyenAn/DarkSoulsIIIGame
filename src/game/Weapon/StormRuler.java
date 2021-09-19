package game.Weapon;


import edu.monash.fit2099.engine.Action;
import game.ChargeAction;

import java.util.List;

public class StormRuler extends Sword {
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public StormRuler(String name, char displayChar, int damage, String verb, int hitRate,String swordPassiveSkill) {
        super("StormRuler", '7', 50, "blow", 60, "critical strike, Dullness");

    }

    public StormRuler() {
        super("StormRuler", '7', 50, "blow", 60, "critical strike, Dullness");

    }
    /**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     */
    @Override
    public int damage() {
        int newDamage= criticalStrike();
        return newDamage;
    }


    /**
     * Get an action or skill from the weapon that will be used against one target.
     * This method allows weapon instance to interact with Actor class.
     * It can be used to have actionable special attack, heal, silence, etc. to a target
     *
     * @param target    the target actor
     * @param direction the direction of target, e.g. "north"
     * @return null by default because a weapon doesn't have any active skill. Otherwise, return a WeaponAction instance.
     * @see WeaponItem#allowableActions for a self-direction skill instead of using this method (recommendation)
     */


    /**
     * Getter.
     * <p>
     * Returns an unmodifiable copy of the actions list so that calling methods won't
     * be able to change what this Item can do without the Item checking.
     *
     * @return an unmodifiable list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        getAllowableActions().add(new ChargeAction());
//        getAllowableActions().add(new WindSlashAction());
        return super.getAllowableActions();
    }
}

