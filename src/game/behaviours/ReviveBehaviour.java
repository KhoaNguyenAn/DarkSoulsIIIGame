package game.behaviours;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Abilities;
import game.interfaces.Behaviour;
/**
 * Class representing revive behaviour
 * @author Dongzheng Wu
 */
public class ReviveBehaviour extends Action implements Behaviour{
	/**
	 * Random generator
	 */
	private final Random random = new Random();
	/**
	 * Override get action method to achieve revive feature
	 * @see Behaviour
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		// Check the actor's status
		if(!actor.isConscious() && actor.hasCapability(Abilities.REVIVE)) {
			
			// If revived, heal the actor and remove revive ability, otherwise return null
			if(random.nextInt(100)<50) {
				actor.heal(Integer.MAX_VALUE);
				actor.removeCapability(Abilities.REVIVE);
				return this;
			}
		}
		return null;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		return null;
	}
	@Override
	public String menuDescription(Actor actor) {
		return null;
	}
}
