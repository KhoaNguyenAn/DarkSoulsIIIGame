package game.enemies;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Location;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.terrains.Chest;
/**
 * Class representing a mimic
 * @author Dongzheng Wu
 * @see Enemies
 */
public class Mimic extends Enemies implements Resettable{
	/**
	 * Attribute to store the location of chest
	 */
	private Location location;
	/**
	 * Constructor of Mimic
	 * @param location the location of chest
	 */
	public Mimic(Location location) {
		super("Mimic", 'M', 100, 200);
		behaviours.add(new WanderBehaviour());
		// After dead, mimic will drop token of souls
		this.addCapability(Abilities.DROP_SOULS);
		this.location = location;
	}
	/**
	 * Override playTurn method to achieve behaviour and reset feature.
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		if(this.hasCapability(Status.SOFTRESET)) {
			map.removeActor(this);
			location.setGround(new Chest());
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
	 * Override intrinsicWeapon method, set damage to 55 for Mimic.
	 * @return An InstrinsicWeapon object
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(55, "kick");
	}
}
