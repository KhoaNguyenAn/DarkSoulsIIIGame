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
 * An undead class represent an undead
 * @author Dongzheng Wu
 * @see Enemies
 */
public class Undead extends Enemies implements Resettable{
	/**
	 * A random generator
	 */
	private final Random random = new Random();
	/** 
	 * Constructor of Undead
	 * Undeads are represented by an 'u' with 50 hit points, 50 souls.
	 */
	public Undead() {
		super("Undead", 'u', 50, 50);
		// Add instant die and wander behaviour
		behaviours.add(new InstantDieBehaviour());
		behaviours.add(new WanderBehaviour());
	}
	/**
	 * FIXED: The Undead is able to attack and follow the player, and they will be spawn from cemetery.
	 * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// If soft reset is triggered, remove all the undead from map
		if(this.hasCapability(Status.SOFTRESET)) {
			map.removeActor(this);
			return new DoNothingAction();
		}
		for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
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
	/**
	 * Add SOFTRESET status for soft reset.
	 */
	@Override
	public void resetInstance() {
		this.addCapability(Status.SOFTRESET);
	}
	/**
	 * Undead will be removed from map, so return false.
	 */
	@Override
	public boolean isExist() {
		return false;
	}
}
