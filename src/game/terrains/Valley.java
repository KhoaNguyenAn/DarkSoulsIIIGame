package game.terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;

/**
 * The gorge or endless gap that is dangerous for the Player.
 * Class representing valley
 */
public class Valley extends Ground {
	/**
	 * Constructor for valley
	 */
	public Valley() {
		super('+');
	}

	/**
	 * FIXED: Now valley allow player to enter but not allow other actor to enter
	 * @param actor 	the Actor to check
	 * @return true if allowed enter, otherwise return false
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		if(actor.hasCapability(Abilities.FALL))
			return true;
		else
			return false;
	}
	/**
	 * Check the player is step into valley or not, kill the player if it is
	 * @param The location of current valley
	 */
	public void tick(Location location) {
		Actor player = location.getActor();
		if(player != null) {
			player.hurt(Integer.MAX_VALUE);
		}
	}
}
