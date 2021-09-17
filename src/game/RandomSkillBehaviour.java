package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.interfaces.Behaviour;

public class RandomSkillBehaviour extends Action implements Behaviour{
	private Actor target;

	private final Random random = new Random();
	
	public RandomSkillBehaviour(Actor subject) {
		this.target = subject;
	}
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(random.nextInt(100)<50) {
			return actor.getWeapon().getActiveSkill(target, null);
		}
		return null;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

}
