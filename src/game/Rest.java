package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Actor;

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
		actor.heal(Integer.MAX_VALUE/2);
		map.moveActor(actor, map.at(38, 11));
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
