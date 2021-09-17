package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;

/**
 * The gorge or endless gap that is dangerous for the Player.
 * Valley class represent valley, extends from Ground
 * @see Ground
 */
public class Valley extends Ground {
	/**
	 * Constructor for valley
	 */
	public Valley() {
		super('+');
	}

	/**
	 * FIXME: At the moment, the Player cannot enter it. It is boring.
	 * FIXED: Now valley allow player to enter but not allow other actor to enter.
	 * @param actor the Actor to check
	 * @return false or actor cannot enter.
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		if(actor.hasCapability(Abilities.FALL))
			return true;
		else
			return false;
	}
	/**
	 * Check the player is step into valley or not, kill the player if it is.
	 * @param The location of current valley
	 */
	public void tick(Location location) {
		Actor player = location.getActor();
		if(player != null) {
			player.hurt(Integer.MAX_VALUE);
			System.out.println("You fall into valley and dead"); // Used to indicate this is working.
		}
	}
}
