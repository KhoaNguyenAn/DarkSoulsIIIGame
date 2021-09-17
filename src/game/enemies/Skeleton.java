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
import game.Gun;
import game.MagicWand;
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
		randomWeapon();
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		behaviours.add(0, new ReviveBehaviour());
		for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}
	/**
	 * A method that allow skeleton to use random weapons
	 * TODO: Need modified to broad sword and giant axe later
	 */
	private void randomWeapon() {
		if(random.nextInt(100)<50)
			this.addItemToInventory(new Gun());
		else
			this.addItemToInventory(new MagicWand());
	}
}
