package game.Weapon;


import edu.monash.fit2099.engine.*;
import game.actions.SwapWeaponAction;
import game.enums.Abilities;
import game.skills.ChargeAction;
import game.skills.WindSlashAction;

import java.util.ArrayList;
import java.util.List;

import static game.enums.Abilities.BOSS;
import static game.enums.Abilities.CHARGE;

public class StormRuler extends Sword {

    private Actor target;
    int chargeCount=0;

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
        super("StormRuler", '7', 70, "blow", 60, "critical strike, Dullness");

    }

    public StormRuler() {
        super("StormRuler", '7', 70, "blow", 60, "critical strike, Dullness");

    }
    /**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     */
    @Override
    public int damage() {
        int newDamage= criticalStrike();
        if(target == null)
        	return newDamage/2;
        if (target.hasCapability(Abilities.YHORM)) {
        return newDamage;
        }
        else return newDamage/2;
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
     * Get an action or skill from the weapon that will be used against one target.
     * This method allows weapon instance to interact with Actor class.
     * It can be used to have actionable special attack, heal, silence, etc. to a target
     *
     * @param target    the target actor
     * @param direction the direction of target, e.g. "north"
     * @return null by default because a weapon doesn't have any active skill. Otherwise, return a WeaponAction instance.
//     * @see WeaponItem#allowableActions for a self-direction skill instead of using this method (recommendation)
//     */int charge=0;

    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        this.target=target;
        ChargeAction chargeAction=new ChargeAction(new StormRuler());


        if (chargeAction.getChargeCounter()==3){
            chargeAction.setChargeCounter(0);
            return new WindSlashAction(new StormRuler(), target);
        }

return null;
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
        List<Action> actions= new ArrayList<>();
       if (hasCapability(CHARGE)){
            ChargeAction chargeAction = new ChargeAction(new StormRuler());
           if (chargeAction.getChargeCounter()<3){
               actions.add(chargeAction);
           }

        }

        return actions;
    }
    /**
     * Override getPickUpAction to allow swap the weapon
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
		if(portable) {
		    addCapability(CHARGE);
            return new SwapWeaponAction(this);
        }
		
		return null;
	}

    /**
     * Add a Capability to this Item.
     *
     * @param capability the Capability to add
     */
    /**
     * Add a Capability to this Item.
     *
     * @param capability the Capability to add
     */
    @Override
    public void addCapability(Enum<?> capability) {
        super.addCapability(capability);
    }

    /**
     * Casts this Item to a Weapon if possible.
     *
     * @return a reference to the current Item as type Weapon, or null if this Item isn't a Weapon
     */
    @Override
    public Weapon asWeapon() {
        return super.asWeapon();
    }
}

