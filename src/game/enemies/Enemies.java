package game.enemies;
import java.util.ArrayList;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.AttackAction;
import game.AttackBehaviour;
import game.FollowBehaviour;
import game.RandomSkillBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
/**
 * Abstract Enemies class represent enemies
 * @author Dongzheng Wu
 * @see Actor
 */
public abstract class Enemies extends Actor implements Resettable{
	/**
	 * The number of souls to reward after enemies was defeated
	 */
	private int souls;
	/**
	 * The behaviours that the current enemy has
	 */
	protected ArrayList<Behaviour> behaviours = new ArrayList<>();
	/**
	 * A constructor to create enemies
	 * @param name 			the name of enemies
	 * @param displayChar   the display character of enemies
	 * @param hitPoints 	the hit points of enemies
	 * @param souls 		the number of souls reward
	 */
	public Enemies(String name, char displayChar, int hitPoints, int souls) {
		super(name, displayChar, hitPoints);
		this.souls = souls;
		registerInstance();		// Register enemies to reset list so that they can be reset
	}
	/**
	 * Override getAllowableActions to enemy, if other actor has HOSTILE_TO_ENEMY status, they can
	 * attack this enemy. 
	 * This method also used to add some common behaviours of different enemies like follow and 
	 * attack behaviour.
	 * @param otherActor the Actor that might be performing attack
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			
			// Allow player to attack enemies
			actions.add(new AttackAction(this,direction));
			if(!(otherActor.getWeapon() instanceof IntrinsicWeapon)) {
				
				// Allow player to use weapon skill if they had
				actions.add(otherActor.getWeapon().getActiveSkill(this, direction)); 
			}
			
			// Add follow and attack behaviour to enemies
			behaviours.add(0, new FollowBehaviour(otherActor));
			behaviours.add(0, new AttackBehaviour(otherActor));
			
			// Allow enemies to use active skills randomly
			if(!(this.getWeapon() instanceof IntrinsicWeapon) && !(this.hasCapability(Abilities.BOSS)))
				behaviours.add(0, new RandomSkillBehaviour(otherActor));
		}
		return actions;
	}
	/**
	 * Override toString method so that it can display current hitpoints, maximum hitpoints and the
	 * weapon that enermy used.
	 * @return String of details
	 */
	@Override
	public String toString() {
		String message = "(no weapon)";
		if(!(this.getWeapon() instanceof IntrinsicWeapon))
			message = "(holding " + this.getWeapon().toString()+")";
		return name + "(" + hitPoints + "/" + maxHitPoints + ")" + message;
	}
}
