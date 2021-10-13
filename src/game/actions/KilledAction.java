package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
/**
 * Class representing the KilledAction (the action after the enemies are killed)
 * @author Dongzheng Wu
 */
public class KilledAction extends Action{

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + "is killed";
	}

}
