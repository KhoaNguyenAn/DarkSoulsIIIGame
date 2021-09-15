package game.enemies;

import java.util.ArrayList;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.AttackAction;
import game.ReviveBehaviour;
import game.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
/**
 * Skeleton class represent a skeleton, extends from abstract Enemies class
 * @author Dongzheng Wu
 * @version 1.0.1
 * @see Enemies
 */
public class Skeleton extends Enemies{

	public ArrayList<Behaviour> behaviours = new ArrayList<>();
	private int reviveTimes = 1;
	public Skeleton(String name) {
		super(name, 's', 100, 250);
		behaviours.add(new ReviveBehaviour());
//		behaviours.add(new WanderBehaviour());
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
	public int getReviveTimes() {
		return reviveTimes;
	}
	
	public void revived() {
		reviveTimes = 0;
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			actions.add(new AttackAction(this,direction));
		}
		return actions;
	}
	
}
