package game.enemies;
import java.util.ArrayList;

import edu.monash.fit2099.engine.Actor;
import game.WanderBehaviour;
import game.interfaces.Behaviour;
/**
 * Abstract Enemies class represent enemies, extends from abstract Actor class
 * @author Dongzheng Wu
 * @version 1.0.1
 * @see Actor
 */
public abstract class Enemies extends Actor{
	
	/**
	 * The number of souls to reward after enemies was defeated
	 */
	private int souls;
	public ArrayList<Behaviour> behaviours = new ArrayList<>();

	
	/**
	 * A constructor to create enemies
	 * @param name the name of enemies
	 * @param displayChar the display character of enemies
	 * @param hitPoints the hit points of enemies
	 * @param souls the number of souls reward
	 */
	public Enemies(String name, char displayChar, int hitPoints, int souls) {
		super(name, displayChar, hitPoints);
		this.souls = souls;
		behaviours.add(new WanderBehaviour());
	}
	
}
