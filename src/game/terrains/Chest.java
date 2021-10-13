package game.terrains;

import java.util.Random;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.TokenOfSoul;
import game.actions.OpenAction;
import game.enemies.Mimic;
import game.enums.Status;
/**
 * Class representing chest
 * @author Dongzheng Wu
 */
public class Chest extends Ground{
	/**
	 * Random generator
	 */
	private final Random random = new Random();
	/**
	 * Constructor of Chest
	 */
	public Chest() {
		super('?');
	}
	/**
	 * Override tick method to check if the chest is opened and trigger two different scenarios
	 */
	@Override
	public void tick(Location location) {
		// If the chest has OPENED status, set it to dirt and triggered the feature(either spawn mimic or place souls)
		if(this.hasCapability(Status.OPENED)){
			location.setGround(new Dirt());
			if(random.nextInt(100)<50) {
				int counter = random.nextInt(3);
				for(int i=0; i <= counter; i++) {
					location.addItem(new TokenOfSoul(100));
				}
			}
			else {
				location.addActor(new Mimic(location));
			}
		}
	}
	/**
	 * Override canActorEnter method to avoid any actor enter the chest
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	/**
	 * Override allowableActions method to allow player open it
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new OpenAction(this));
	}
}
