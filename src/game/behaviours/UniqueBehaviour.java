package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;
import game.interfaces.Behaviour;
/**
 * Class representing unique behaviour (of lord of cinder)
 * @author Dongzheng Wu
 */
public class UniqueBehaviour implements Behaviour{
	/**
	 * Attributes, the current hit points of behaviour owner
	 */
	private int hitPoints;
	/**
	 * Attributes, the maximum hit points of behaviour owner
	 */
	private int maxHitPoints;
	/**
	 * Constructor
	 * @param subject		the target to be used skills
	 * @param hitPoints		the current hit points of behaviour owner
	 * @param maxHitPoints	the maximum hit points of behaviour owner
	 */
	public UniqueBehaviour(int hitPoints, int maxHitPoints) {
		this.hitPoints = hitPoints;
		this.maxHitPoints = maxHitPoints;
	}
	/**
	 * Override get action method to achieve unique feature
	 * @see Behaviour
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// If actor is not valid, return null
		if(!map.contains(actor) || !actor.isConscious())
			return null;
		
		// To get the target from adjacent
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if(destination.getActor() != null) {
            	 if(destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
            		 if((hitPoints < maxHitPoints/2))
            				return actor.getWeapon().getActiveSkill(destination.getActor(), null);
            			}
            	 }
            }
		return null;
	}
}
