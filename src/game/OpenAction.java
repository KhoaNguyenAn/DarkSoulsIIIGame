package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;
import game.terrains.Chest;
/**
 * Class representing open action (open the chest)
 * @author Dongzheng Wu
 */
public class OpenAction extends Action{
	/**
	 * Random generator
	 */
	Chest chest = null;
	/**
	 * Class constructor
	 * @param chest the chest object
	 */
	public OpenAction(Chest chest) {
		this.chest = chest;
	}
	/**
	 * Override execute method to add the status of chest
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		chest.addCapability(Status.OPENED);
		return menuDescription(actor);
	}
	/**
	 * Override menuDescription method
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " open the Chest";
	}

}
