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
 * The gorge or endless gap that is dangerous for the Player.
 */
public class Cemetery extends Ground {
	private final Random random = new Random();
	public Cemetery() {
		super('c');
	}

	@Override
	public boolean canActorEnter(Actor actor){
		return false;
	}
	
	public void tick(Location location) {
		if(random.nextInt(4)==1) {
			location.addActor(new Undead("Undead"));
		}
	}
}