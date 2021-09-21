package game.behaviours;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.AttackAction;
import game.enums.Status;
import game.interfaces.Behaviour;
/**
 * Class representing random skill behaviour
 * @author Dongzheng Wu
 */
public class RandomSkillBehaviour implements Behaviour{
	/**
	 * Random generator
	 */
	private final Random random = new Random();
	/**
	 * Constructor
	 * @param subject		The target to be used skills
	 */
	public RandomSkillBehaviour() {
	}
	/**
	 * Override get action method to achieve random skill feature
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
            		 if(random.nextInt(100)<50) {
            				return actor.getWeapon().getActiveSkill(destination.getActor(), null);
            			}
            	 }
            }
        }
		return null;
	}
}
