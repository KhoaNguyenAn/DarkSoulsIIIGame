package game.terrains;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.MoveActorAction;
import game.actions.Rest;
import game.actions.lightTheBonfire;
/**
 *  Public Bonfire Class - Extends from Ground
 */
public class Bonfire extends Ground {

	private String name = "";
	private Boolean activateStatus;
	private GameMap firstGameMap;
	private GameMap secondGameMap;
 
    /**
	 *  Letter "B" is the Bonfire
	 */
	public Bonfire() {
        super('B');
		this.activateStatus = true;
    }

	public Bonfire(String name,Boolean activateStatus, GameMap firstMap, GameMap secondMap) {
        super('B');
		this.name = name;
		this.activateStatus = activateStatus;
		this.firstGameMap = firstMap;
		this.secondGameMap = secondMap;
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
		if (this.activateStatus == false) {
			return new Actions(new lightTheBonfire(actor,this));
		}
		else {
			Actions actions = new Actions();
			actions.add(new Rest());
			if (this.name.equals("Profane Capital's Bonfire")) {
				actions.add(new MoveActorAction(this.firstGameMap.at(38,11), "Firelink Shrine's Bonfire"));
			}
			else {
				actions.add(new MoveActorAction(this.secondGameMap.at(40,0), "Profane Capital's Bonfire"));
			}
			return actions;
		}
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean isActivateStatus() {
		return this.activateStatus;
	}

	public Boolean getActivateStatus() {
		return this.activateStatus;
	}

	public void setActivateStatus(Boolean activateStatus) {
		this.activateStatus = activateStatus;
	}

}
