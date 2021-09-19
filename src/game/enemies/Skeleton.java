package game.enemies;

import edu.monash.fit2099.engine.*;
import game.Weapon.BroadSword;
import game.Weapon.GiantAxe;
import game.behaviours.FollowBehaviour;
import game.behaviours.ReviveBehaviour;
import game.behaviours.WanderBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

import java.util.Random;
/**
 * Skeleton class represent a skeleton
 * @author Dongzheng Wu
 * @see Enemies
 */
public class Skeleton extends Enemies implements Resettable{
	/**
	 * A random generator
	 */
	private final Random random = new Random();
	/**
	 * Location is used to store the initial position of skeleton
	 */
	private Location location = null;
	/**
	 * Constructor of skeleton
	 * Skeletons are represented by 's' with 100 hit points, and 250 souls.
	 */
	public Skeleton() {
		super("Skeleton", 's', 100, 250);
		this.addCapability(Abilities.REVIVE);	// Skeleton has ability to revive
		behaviours.add(new WanderBehaviour());	// Add wander bahaviours
		randomWeapon();		// Skeleton will hold random weapon
	}
	/**
	 * Override playTurn method
	 * @sees edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Add location of skeleton
		if (location == null)
				location = map.locationOf(this);
		
		// If soft reset is triggered, move skeleton to original position, heal them, remove status.
		if(this.hasCapability(Status.SOFTRESET)) {
			map.moveActor(this, location);
			this.removeCapability(Status.SOFTRESET);
			this.heal(maxHitPoints);
			for (int i = 0; i < behaviours.size(); i++) {
	        	if(behaviours.get(i) instanceof FollowBehaviour)
	        		behaviours.remove(i);
	        }
			return new DoNothingAction();
		}
		
		// Add the revive behaviour, it should has highest priority, otherwise it may corrupt system
		behaviours.add(0, new ReviveBehaviour());
		for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
	/**
	 * A method that allow skeleton to hold random weapons
	 * TODO: Need modified to broad sword and giant axe later
	 */
	private void randomWeapon() {
		if(random.nextInt(100)<50)
			this.addItemToInventory(new BroadSword());
		else
			this.addItemToInventory(new GiantAxe());
	}
}
