package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.interfaces.Behaviour;
/**
 * Class representing unique behaviour (of lord of cinder)
 * @author Dongzheng Wu
 */
public class UniqueBehaviour implements Behaviour{
	/**
	 * Attributes, the target to be used skills
	 */
	private Actor target;
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
	public UniqueBehaviour(Actor subject, int hitPoints, int maxHitPoints) {
		this.target = subject;
		this.hitPoints = hitPoints;
		this.maxHitPoints = maxHitPoints;
	}
	/**
	 * Override get action method to achieve unique feature
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
		
		// Check the current hit points
		if((hitPoints < maxHitPoints/2) && currentDistance < 2)
			return actor.getWeapon().getActiveSkill(target, null);
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
