package game.Weapon;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.WeaponAction;
import game.EmberFormAction;
import game.enums.Status;

public class YhormsGiantMachete extends Axe {
    private Actor actor;
    /**
     * Constructor.
     *
     */
    public YhormsGiantMachete(Actor actor1) {

        super("YhormsGiantMachete", 'M', 95, "Beat", 60);
        actor=actor1;
    }


    /**
     * Get an action or skill from the weapon that will be used against one target.
     * This method allows weapon instance to interact with Actor class.
     * It can be used to have actionable special attack, heal, silence, etc. to a target
     *
     * @param target    the target actor
     * @param direction the direction of target, e.g. "north"
     * @return null by default because a weapon doesn't have any active skill. Otherwise, return a WeaponAction instance.
     */
    @Override
    public WeaponAction getActiveSkill(Actor target, String direction) {
        return new EmberFormAction(new YhormsGiantMachete(actor));
    }

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
     * Returns the chance to hit the target in integer. Use it altogether with nextInt() method.
     *
     * @return Integer, such as
     */
    @Override
    public int chanceToHit() {
        if (actor.hasCapability(Status.EMBER_FORM)) {
//            int newChanceToHit= chanceToHit()+30;
//            return newChanceToHit;
            return super.chanceToHit()+30;
        }
        else
        	return super.chanceToHit();
    }
}
