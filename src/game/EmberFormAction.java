package game;

import edu.monash.fit2099.engine.*;
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


    @Override
    public String execute(Actor actor, GameMap map) {
        Weapon weapon = actor.getWeapon();


//        Location bossLocation = map.locationOf(actor);
//
//        for (int x = bossLocation.x() - 1; x <= bossLocation.x() + 1; x++) {
//            for (int y = bossLocation.y() - 1; y <= bossLocation.y() + 1; y++) {
//                if (map.at(x, y).getGround().getDisplayChar() == '.') {
//                    map.at(x, y).setGround(new BurningDirt());
//                }
//            }
//        }

        // To get the target from adjacent
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

                    if (destination.getGround().getDisplayChar()=='.'){
                        destination.setGround(new BurningDirt());
                    }

            }
        return null;
        }



    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Player gets burned for 25 damage";
    }
}
