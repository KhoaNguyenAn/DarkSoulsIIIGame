package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import game.managers.BonfireManager;
import game.managers.ResetManager;
import edu.monash.fit2099.engine.Actor;

/**
 * Rest - A action that allow user to reset
 */
public class Rest extends Action {

    public void Rest(){
        
    }
    
    /**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
    @Override
	public String execute(Actor actor, GameMap map) {
    	BonfireManager bonfireManager = BonfireManager.getInstance();
    	bonfireManager.appendBonfireInstance(map, map.locationOf(actor));
		actor.heal(Integer.MAX_VALUE/2);
        ResetManager manager = ResetManager.getInstance();
		manager.run();
        return "Player is resting";
    }

    /**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
    @Override
	public String menuDescription(Actor actor) {
        return "Rest at Bonfire";
    }

}
