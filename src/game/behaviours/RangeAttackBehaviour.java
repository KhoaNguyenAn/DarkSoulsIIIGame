package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.interfaces.Behaviour;
import game.skills.RangeAttack;
/**
 * Class representing range attack behaviour (it is for Aldrich only currently)
 * @author fzl84
 *
 */
public class RangeAttackBehaviour implements Behaviour{
	/**
	 * Attribute the target to be attacked
	 */
	private Actor target;
	/**
	 * Constructor of class
	 * @param target
	 */
	public RangeAttackBehaviour(Actor target) {
		this.target = target;
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		int currentDistance = distance(here, there);
		// If the target inside the range, return range attack
		if(currentDistance <= 3)
			return new RangeAttack(target, null);

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
