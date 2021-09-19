package game.behaviours;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Behaviour;
/**
 * Class representing instant die behaviour
 * @author Dongzheng Wu
 */
public class InstantDieBehaviour extends Action implements Behaviour{
	/**
	 * Random generator
	 */
	private final Random random = new Random();
	/**
	 * Override get action method to achieve instant die feature
	 * @see Behaviour
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(random.nextInt(100)<10) {
			map.removeActor(actor);
			return this;
		}
		return null;
	}
	/**
	 * Override execute method to get menu description
	 * @see Behaviour
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " is dead instantly";
	}
}
