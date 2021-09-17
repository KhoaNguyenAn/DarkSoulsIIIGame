package game.enemies;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.AttackAction;
import game.AttackBehaviour;
import game.FollowBehaviour;
import game.ReviveBehaviour;
import game.WanderBehaviour;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.enums.Abilities;
/**
 * Skeleton class represent a skeleton, extends from abstract Enemies class
 * @author Dongzheng Wu
 * @see Enemies
 */
public class Skeleton extends Enemies{
	/**
	 * Set random variable
	 */
	private final Random random = new Random();
	public Skeleton() {
		super("Skeleton", 's', 100, 250);
		this.addCapability(Abilities.REVIVE);
		behaviours.add(new WanderBehaviour());
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		behaviours.add(0, new ReviveBehaviour());
		for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		
		if(!this.isConscious())
			map.removeActor(this);
		
		return new DoNothingAction();
	}


	
//	@Override
//	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//		
//		Actions actions = new Actions();
//		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
//			actions.add(new AttackAction(this,direction));
//		}
//		return actions;
//	}
	
}
