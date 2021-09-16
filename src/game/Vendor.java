package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

import static game.enums.Abilities.BUY_ITEMS;

public class Vendor extends Actor {


    private List <Action> vendorActions;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Vendor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
//          vendorActions.add()
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {

        Actions actions = new Actions();
        // When Player is next to the Vendor
        if(otherActor.hasCapability(BUY_ITEMS)) {
            actions.add(new PurchaseAction());
        }
        return actions;


    }

}
