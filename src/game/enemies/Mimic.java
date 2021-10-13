package game.enemies;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Location;
import game.TokenOfSoul;
import game.actions.KilledAction;
import game.behaviours.WanderBehaviour;
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
	 * Random generator
	 */
	private final Random random = new Random();
	/**
	 * Constructor of Mimic
	 * @param location the location of chest
	 */
	public Mimic(Location location) {
		super("Mimic", 'M', 100, 200);
		behaviours.add(new WanderBehaviour());
		this.location = location;
	}
	/**
	 * Override playTurn method to achieve behaviour and reset feature.
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// If this enemy is defeated, place the token of souls
		if(!this.isConscious()) {
			placeTokenOfSoul(map);
			map.removeActor(this);
			return new KilledAction();
		}
		// Handle the soft reset feature - remove alive mimic and convert the original location to chest
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
	/**
	 * Method to place several token of souls after mimic is killed
	 * @param map
	 */
	public void placeTokenOfSoul(GameMap map) {
		int counter = random.nextInt(3);
		for(int i=0; i <= counter; i++) {
			map.locationOf(this).addItem(new TokenOfSoul(100));
		}
	}
}
