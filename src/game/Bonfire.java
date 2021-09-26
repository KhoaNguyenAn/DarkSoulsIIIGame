package game;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actions;
import game.Rest;
/**
 *  Public Bonfire Class - Extends from Ground
 */
public class Bonfire extends Ground {


    /**
	 *  Letter "B" is the Bonfire
	 */
	public Bonfire() {
        super('B');
    }

    /**
	 * Method to allow Player to enter
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		return true;
	}

    /**
	 * Returns an empty Action list.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a new, empty collection of Actions
	 */
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new Rest());
	}

}
