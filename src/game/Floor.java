package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.enums.Abilities;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
	}
	/**
	 * Check the actor has permission to enter the floor
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		if(actor.hasCapability(Abilities.ENTER))
			return true;
		else
			return false;
	}
}
