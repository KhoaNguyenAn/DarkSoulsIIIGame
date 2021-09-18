package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.interfaces.Behaviour;

public class UniqueBehaviour extends Action implements Behaviour{
	private Actor target;
	private int hitPoints;
	private int maxHitPoints;
	
	public UniqueBehaviour(Actor subject, int hitPoints, int maxHitPoints) {
		this.target = subject;
		this.hitPoints = hitPoints;
		this.maxHitPoints = maxHitPoints;
	}
	@Override
	public Action getAction(Actor actor, GameMap map) {
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);
		int currentDistance = distance(here, there);
		if((hitPoints < maxHitPoints/2) && currentDistance < 2)
			return actor.getWeapon().getActiveSkill(target, null);
		return null;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + "is enrages the weapon!";
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
