package game.enemies;

import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.StunAction;
import game.Weapon.YhormsGiantMachete;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.UniqueBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

/**
 * The boss of Design o' Souls
 * FIXED: Features are implemented.
 * TODO: Could it be an abstract class? If so, why and how? (Answered in design rationale)
 * LordOfCinder represents a lord of cinder (boss)
 */
public class LordOfCinder extends Enemies implements Resettable{
	/**
	 * Location is used to store the initial position of load of cinder
	 */
	private Location location = null;
    /**
     * Constructor, it is created in application, Worth 5000 souls.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, 5000);
        this.addCapability(Abilities.BOSS);		// Used for some boss feature.
        this.addItemToInventory(new YhormsGiantMachete());
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action	 the action this enemy going to do
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
    	// Add location of Lord of cinder
    	if (location == null)
			location = map.locationOf(this);
    	
    	if(this.hasCapability(Status.STUNNED)) {
    		this.removeCapability(Status.STUNNED);
    		return new StunAction();
    	}
    	
    	// If soft reset is triggered, move this enemy to original position, heal it, remove status.
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
        for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
    	return new DoNothingAction();
    }
    /**
     * Override getAllowableActions to achieve unique behaivour feature
     * @see edu.monash.fit2099.engine.Actor#getAllowableActions(Actor otherActor, String direction, GameMap map) 
     */
    @Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		
		// HOSTLE_TO_ENEMY status avoid enemies attack each other
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			
			// Allow player to attack this enemy
			actions.add(new AttackAction(this,direction));
			
			// Allow player to use weapon skill to this enemy if they had
			if(!(otherActor.getWeapon() instanceof IntrinsicWeapon)) {
				actions.add(otherActor.getWeapon().getActiveSkill(this, direction));
			}
			
			// Add follow, attack and unique behaviour to lord of cinder
			behaviours.add(new FollowBehaviour(otherActor));
			behaviours.add(0, new AttackBehaviour());
			behaviours.add(0, new UniqueBehaviour(hitPoints, maxHitPoints));
		}
		return actions;
	}
}
