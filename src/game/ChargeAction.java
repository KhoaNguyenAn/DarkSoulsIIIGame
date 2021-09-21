package game;

import edu.monash.fit2099.engine.*;

public class ChargeAction  extends WeaponAction {


    private static int chargeCounter;

    /**
     * Constructor
     *
     * @param weaponItem the weapon item that has capabilities
     */
    public ChargeAction(WeaponItem weaponItem) {
        super(weaponItem);
         int chargeCounter=0;
    }

    public int getChargeCounter() {
        return chargeCounter;
    }

    public  void setChargeCounter(int chargeCounter) {
        ChargeAction.chargeCounter = chargeCounter;
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

       ChargeAction.chargeCounter++;
        if (ChargeAction.chargeCounter==3){

            return "Charge 3/3 Finish!";
        }else {

            return "StormRuler Charged"+ChargeAction.chargeCounter+"/3";
        }
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " charges  Storm Ruler ";
    }


}
