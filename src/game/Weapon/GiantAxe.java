package game.Weapon;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import game.SpinAttackAction;

import java.util.List;

public class GiantAxe extends Axe {
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public GiantAxe(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);



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
    @Override
    public SpinAttackAction getActiveSkill(Actor target, String direction) {
        return  new SpinAttackAction(target,direction);
    }

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
        return super.getAllowableActions();
    }

/**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     */
//    @Override
//    public int damage() {
//        //if at adjacent angle(Spin Attack
//        if(){
//            int adjDamage=damage/2;
//            return adjDamage;
//        }
//        return super.damage();
//    }
}