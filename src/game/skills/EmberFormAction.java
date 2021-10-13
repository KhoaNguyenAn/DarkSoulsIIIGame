package game.skills;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
import game.terrains.BurningDirt;

import java.util.Random;

public class EmberFormAction extends WeaponAction {

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public EmberFormAction(WeaponItem weaponItem) {
        super(weaponItem);
    }


    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();
        actor.addCapability(Status.EMBER_FORM);


        // To get the target from adjacent
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

                    if (destination.getGround().getDisplayChar()=='.'){
                        destination.setGround(new BurningDirt());
                    }

            }
        return menuDescription(actor);
        }



    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "is activates the ember form";
    }
}
