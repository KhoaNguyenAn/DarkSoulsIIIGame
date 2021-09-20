package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.AttackAction;
import game.interfaces.Behaviour;
/**
 * Class representing attack behaviour
 * @author Dongzheng WU
 */
public class AttackBehaviour implements Behaviour{
	/**
	 * Attributes, the actor to be attacked
	 */
	private Actor target;
	
	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to attack
	 */
	public AttackBehaviour(Actor subject) {
		this.target = subject;
	}
	/**
	 * Override get action method to achieve attack feature
	 * @see Behaviour
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// If target or actor is not valid, return null
		if(!map.contains(target) || !map.contains(actor) || !actor.isConscious())
			return null;
		
		// Check the relative position
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		int currentDistance = distance(here, there);
		if(currentDistance <= 2) {
			return new AttackAction(target, null);
		}
		return null;
	}
	/**
	 * Compute the Manhattan distance between two locations.
	 * 
	 * @param a the first location
	 * @param b the first location
	 * @return the number of steps between a and b if you only move in the four cardinal directions.
	 */
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}
}
