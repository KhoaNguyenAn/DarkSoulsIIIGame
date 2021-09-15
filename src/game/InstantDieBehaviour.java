package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.interfaces.Behaviour;

public class InstantDieBehaviour extends Action implements Behaviour{

	private final Random random = new Random();
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(random.nextInt(10)==0) {
			map.removeActor(actor);
			return this;
		}
		else {
			return null;
		}
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return actor.toString() + " is killed instantly";
	}
	
}
