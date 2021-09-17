package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Abilities;
import game.interfaces.Behaviour;
/**
 * ReviveBehaviour is used to revive the skeleton with 50% chance
 * @author Dongzheng Wu
 * @see Skeleton
 */
public class ReviveBehaviour extends Action implements Behaviour{
	/**
	 * A random generator
	 */
	private final Random random = new Random();
	/**
	 * Override the action, if revived, heal the actor, remove Revive abilities because they only
	 * allowed to revive once. Otherwise remove the unconscious actor from map. If the actor is
	 * conscious just return null
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!actor.isConscious()) {
			if(random.nextInt(100)<50) {
				actor.heal(Integer.MAX_VALUE);
				actor.removeCapability(Abilities.REVIVE);
				return this;
			}
			else {
				map.removeActor(actor);
				return this;
			}
		}
		else
			return null;
	}
	/**
	 * Execute the menu description
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}
	
	@Override
	public String menuDescription(Actor actor) {
		if(actor.isConscious())
			return actor + " is revived !";
		return actor + "is die peacefully ~";
	}

}
