package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.enemies.Skeleton;
import game.interfaces.Behaviour;

public class ReviveBehaviour extends Action implements Behaviour{

	private final Random random = new Random();
	
	
	public Action getAction(Skeleton skeleton, GameMap map) {
		if(skeleton.isConscious() && skeleton.getReviveTimes()!=0) {
			return null;
		}
		else {
			if(random.nextInt(2)==1) {
				skeleton.heal(Integer.MAX_VALUE);
				skeleton.revived();
				return this;
			}
			return null;
		}
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor.toString() + " is revive!";
	}

	@Override
	public Action getAction(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		return null;
	}

}
