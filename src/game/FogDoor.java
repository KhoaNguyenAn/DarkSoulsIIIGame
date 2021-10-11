package game;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import game.enums.Abilities;
import edu.monash.fit2099.engine.Actions;
public class FogDoor extends Ground {
    private Location targetLocation;
    private String locationName;
    private GameMap secondGameMap;
	
    public FogDoor(Location targetLocation,String locationName, GameMap secondGameMap) {
        super('=');
        this.targetLocation = targetLocation;
        this.locationName = locationName;
        this.secondGameMap = secondGameMap;
    }

    /**
	 * Method to allow Player to enter
	 */
	@Override
	public boolean canActorEnter(Actor actor){
		if(actor.hasCapability(Abilities.ENTER))
			return true;
		else
			return false;
	}

    @Override
	public Actions allowableActions(Actor actor, Location location, String direction){
		return new Actions(new MoveActorAction(this.secondGameMap.at(this.targetLocation.x(),this.targetLocation.y()), this.locationName));
	}
}
