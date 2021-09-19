package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * Class represent pick up soul action
 * @author Dongzheng Wu
 */
public class PickUpSoulAction extends Action{
	/**
	 * Attribute, the token of souls
	 */
	protected TokenOfSoul item;
	/**
	 * 
	 * Constructor
	 */
	public PickUpSoulAction(TokenOfSoul item) {
		this.item = item;
	}
	/**
	 * Override execute method to allow player pick the token of souls and gain souls.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		map.locationOf(actor).removeItem(item);
		item.transferSouls(actor.asSoul());
		return menuDescription(actor);
	}

	/**
	 * Describe the action in a format suitable for displaying in the menu.
	 *
	 * @see Action#menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return a string, e.g. "Player picks up the rock"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " picks up the " + item;
	}
}
