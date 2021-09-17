package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Behaviour;

public class InstantDieBehaviour extends Action implements Behaviour{
	private final Random random = new Random();

	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		if(random.nextInt(100)<10) {
			map.removeActor(actor);
			return this;
		}
		return null;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor + " is dead instantly";
	}

}
