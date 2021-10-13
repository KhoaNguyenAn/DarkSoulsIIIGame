package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.KilledAction;
import game.StunAction;
import game.Weapon.DarkmoonLongbow;
import game.behaviours.FollowBehaviour;
import game.behaviours.RangeAttackBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;

/**
 * Class representing Aldrich the devourer
 *
 * @author Dongzheng Wu
 */
public class AldrichTheDevourer extends LordOfCinder {
	/**
	 * Location is used to store the initial position of load of cinder
	 */
	private Location location = null;
	/**
	 * Constructor of class
	 */
	public AldrichTheDevourer() {
		super("Aldrich the Devourer", 'A', 350);
		this.addItemToInventory(new DarkmoonLongbow());
	}
	/**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action	 the action this enemy going to do
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
    	// If Boss is defeated
    	if(!this.isConscious()) {
    		isKilled(this, map);
    		return new KilledAction();
    	}
    	// Add location of Lord of cinder
    	if (location == null)
			location = map.locationOf(this);
    	// If stunned, do nothing and shows the stunned status, only used for Yhorm the gaint
    	if(this.hasCapability(Status.STUNNED) && this.hasCapability(Abilities.YHORM)) {
    		this.removeCapability(Status.STUNNED);
    		return new StunAction();
    	}
    	// If soft reset is triggered, move this enemy to original position, heal it, remove status.
    	if(this.hasCapability(Status.SOFTRESET)) {
			map.moveActor(this, location);
			this.removeCapability(Status.SOFTRESET);
			this.heal(maxHitPoints);
			for (int i = 0; i < behaviours.size(); i++) {
	        	if(behaviours.get(i) instanceof FollowBehaviour)
	        		behaviours.remove(i);
	        }
			return new DoNothingAction();
		}
    	Actor target = getTarget(this, map);
    	// Add the follow and range attack behaivour
    	behaviours.add(0, new FollowBehaviour(target));
    	behaviours.add(0, new RangeAttackBehaviour(target));
        for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
    	return new DoNothingAction();
    }
    /**
     * The method to get the target(player) in certain range
     * @param actor  current actor(AldrichTheDevourer)
     * @param map  game map
     * @return	return actor object(player)
     */
    private Actor getTarget(Actor actor, GameMap map) {
		Location location = map.locationOf(actor);
		for (int y = location.y() - 3; y <= location.y() + 3; y++) {
            for (int x = location.x() - 3; x <= location.x() + 3; x++) {
                Location temp = new Location(map, x, y);
                if (temp.getActor() != null && temp.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return temp.getActor();
                }
            }
        }
		return null;
	}
}
