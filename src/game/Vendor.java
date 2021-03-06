package game;
import game.TradeAction.*;
import edu.monash.fit2099.engine.*;
import game.TradeAction.BuyBroadswordAction;
import game.TradeAction.BuyGiantAxeAction;
import game.TradeAction.IncreaseMaxHpAction;
import game.enums.Abilities;
import game.enums.Status;
public class Vendor extends Actor {


    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Vendor(String name, char displayChar, int hitPoints) {
        super("Vendor", 'F', 0);
        this.addCapability(Status.VENDOR);
    }

    public Vendor() {
        super("Vendor",'F',0);
    	this.addCapability(Status.VENDOR);
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

        return new DoNothingAction();
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
        if (otherActor.hasCapability(Abilities.BUY_ITEMS)) {


            // Add actions for buying items when close to it
            actions.add(new BuyBroadswordAction());
            actions.add(new BuyGiantAxeAction());
            actions.add(new IncreaseMaxHpAction());
            actions.add(new TradeGiantBossAction());
            actions.add(new TradeDevourerBossAction());


        }
        return actions;
    }
}
