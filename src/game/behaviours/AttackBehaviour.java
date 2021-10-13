package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actions.AttackAction;
import game.enums.Status;
import game.interfaces.Behaviour;
/**
 * Class representing attack behaviour
 * @author Dongzheng WU
 */
public class AttackBehaviour implements Behaviour{
	/**
	 * Constructor.
	 * 
	 * @param subject the Actor to attack
	 */
	public AttackBehaviour() {
	}
	/**
	 * Override get action method to achieve attack feature
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
                 	return new AttackAction(destination.getActor(), null);
            	 }
            }
        }
		return null;
	}
}
