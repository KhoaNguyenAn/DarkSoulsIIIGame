package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.managers.BonfireManager;
import game.managers.ResetManager;
import game.terrains.Bonfire;

public class lightTheBonfire extends Action {
    private Bonfire bonfire;

    public lightTheBonfire(Actor actor,Bonfire bonfire) {
        this.bonfire = bonfire;
    }

    @Override
	public String execute(Actor actor, GameMap map) {
    	BonfireManager bonfireManager = BonfireManager.getInstance();
    	bonfireManager.appendBonfireInstance(map, map.locationOf(actor));
        this.bonfire.setActivateStatus(true);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Bonfire Lit";
	}
    
}
