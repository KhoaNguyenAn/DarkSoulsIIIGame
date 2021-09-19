package game.terrains;

import java.util.Random;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enemies.Undead;

/**
 * Class representing cemetery
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
	 * Override canActorEnter method so that actors cannot enter
	 * I assume the actor cannot enter the cemetery, otherwise the undead will not be spawn.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return false;
	}
	/**
	 * Method to spawn undead with 25% chance in each turn.
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
