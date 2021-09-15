package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.interfaces.Behaviour;

public class AttackBehaviour implements Behaviour{

	private Actor target;
	
	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to attack
	 */
	public AttackBehaviour(Actor subject) {
		this.target = subject;
	}
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;
		
		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			int newDistance = distance(destination, there);
			if (newDistance < currentDistance) {
				return new AttackAction(target, exit.getName());
			}
		}

		return null;
	}
	
	private int distance(Location a, Location b) {
		return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
	}

}
