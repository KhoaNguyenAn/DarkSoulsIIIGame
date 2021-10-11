package game;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
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
		return true;
	}

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        // appear at the most northern part of the second map with a similar horizontal point/position
        // Enemies cannot interact or pass through with this fog door.
        Actions actions = super.allowableActions(actor, location, map);
        actions.add(new MoveActorAction(this.secondGameMap.at(this.targetLocation.x(),this.targetLocation.y()), "Welcome to "+ this.locationName));
		return actions;
    }
}
