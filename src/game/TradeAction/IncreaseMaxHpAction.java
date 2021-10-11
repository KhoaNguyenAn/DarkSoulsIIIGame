package game.TradeAction;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class IncreaseMaxHpAction extends BuyItemAction {
    public IncreaseMaxHpAction() {

    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.asSoul().subtractSouls(500)== true){
            actor.increaseMaxHp(25);
            return "Increased 25 maxmium hitpoints";
        }
        return "Purchased Failed";
    }

	@Override
	public String menuDescription(Actor actor) {
		return actor +"purchase the Maximum HP (+25)";
	}

}
