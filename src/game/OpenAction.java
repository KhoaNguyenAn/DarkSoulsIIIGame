package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enums.Status;
import game.terrains.Chest;

public class OpenAction extends Action{
	/**
	 * Random generator
	 */
	Chest chest = null;
	
	public OpenAction(Chest chest) {
		this.chest = chest;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		chest.addCapability(Status.OPENED);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " open the Chest";
	}

}
