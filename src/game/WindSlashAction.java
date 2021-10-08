package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class WindSlashAction extends WeaponAction {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public WindSlashAction( WeaponItem weaponItem,Actor target) {
        super(weaponItem);
        this.target=target;


    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();


        int damage = weapon.damage() * 2;
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        target.addCapability(Status.STUNNED);

        if (!target.isConscious()){
			Actions dropActions = new Actions();
				// Check if the target has revive ability or not.
				if(!(target.hasCapability(Abilities.REVIVE))) {
					// In our design, we allow player to drop all the items after dead
					if(target.hasCapability(Abilities.PLAYER)) {
						for (Item item : target.getInventory())
							dropActions.add(item.getDropAction(actor));
						for (Action drop : dropActions)
							drop.execute(target, map);
					}
					// If target is enemy reward souls
					if(!(target.hasCapability(Abilities.PLAYER))) {
						target.asSoul().transferSouls(actor.asSoul()); 		// After defeat enemy, gain souls
					}
				}
				else {
					// If target has abilities to revive, give it extra turn to revive.
					if(target.hasCapability(Abilities.REVIVE)){
						Soul souls = target.asSoul();
						target.playTurn(null, getNextAction(), map, null);
						// If didn't revive, drop the items, remove it and reward souls.
						if(!target.isConscious()) {
							souls.transferSouls(actor.asSoul());
							result += System.lineSeparator() + target + " is killed !";
						}
						else
							result += System.lineSeparator() + target + " is revived !";
					}
				}
		}

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " wind slashes " + target;
    }
}
