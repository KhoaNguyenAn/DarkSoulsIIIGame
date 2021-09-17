package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.enemies.Skeleton;
import game.enemies.Undead;

/**
 * A cemetery can spawn the undeads
 * @author Dongzheng Wu
 */
public class Cemetery extends Ground {
	/**
	 * A random generateor
	 */
	private final Random random = new Random();
	/**
	 * Constructor with 'c' character represent the cemetery
	 */
	public Cemetery() {
		super('c');
	}
	/**
	 * I assume that the actor cannot enter the cemetery, otherwise the undead will not be spawn.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return false;
	}
	/**
	 * Has 25% chance to spawn undead in each turn
	 */
	public void tick(Location location) {
		// Check if there is an actor on the cemetery
		if(location.getActor()==null) {
			if(random.nextInt(100) < 25) {
				Undead undead = new Undead();
				location.addActor(undead);
			}
		}
		
	}
}