package game.enemies;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.AttackAction;
import game.AttackBehaviour;
import game.FollowBehaviour;
import game.InstantDieBehaviour;
import game.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.Random;

/**
 * An undead minion.
 */
public class Undead extends Enemies implements Resettable{
	// Will need to change this to a collection if Undeads gets additional Behaviours.
	/**
	 * A random generator
	 */
	private final Random random = new Random();
	/** 
	 * Constructor.
	 * All Undeads are represented by an 'u' and have 30 hit points.
	 * @param name the name of this Undead
	 */
	public Undead() {
		super("Undead", 'u', 50, 50);
		behaviours.add(new InstantDieBehaviour());
		behaviours.add(new WanderBehaviour());
		registerInstance();
	}
	/**
	 * At the moment, we only make it can be attacked by enemy that has HOSTILE capability
	 * You can do something else with this method.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
//	@Override
//	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//		Actions actions = new Actions();
//		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
//			actions.add(new AttackAction(this,direction));
//		}
//		return actions;
//	}
	/**
	 * Figure out what to do next.
	 * FIXME: An Undead wanders around at random and it cannot attack anyone. Also, figure out how to spawn this creature.
	 * FIXED: The Undead is able to attack and follow the player, and they will be spawn from cemetery.
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Softreset - remove the undead from map
		if(this.hasCapability(Status.SOFTRESET)) {
			map.removeActor(this);
			return new DoNothingAction();
		}
		// loop through all behaviours
		for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		// undead has 10% probability to die instantly every turn
		return new DoNothingAction();
	}
	/**
	 * Override intrinsicWeapon method, set damage to 20 for Undead.
	 * @return An InstrinsicWeapon object
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(20, "punches");
	}

	@Override
	public void resetInstance() {
		// TODO Auto-generated method stub
		this.addCapability(Status.SOFTRESET);
	}

	@Override
	public boolean isExist() {
		// TODO Auto-generated method stub
		return false;
	}

}
