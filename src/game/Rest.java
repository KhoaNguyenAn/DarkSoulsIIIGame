package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Actor;

public class Rest extends Action {
    
    /**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
    @Override
	public String execute(Actor actor, GameMap map) {
        this.resetEnemises();
        this.resetPlayer(actor,map);
        return "Player is resting";
    }

    /**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
    @Override
	public String menuDescription(Actor actor) {
        return "null";
    }

    public void resetPlayer(Actor actor, GameMap map) {

    }

    public void resetEnemises() {

    }
}
