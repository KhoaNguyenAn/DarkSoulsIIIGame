package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Weapon.BroadSword;

public class BuyBroadswordAction extends game.BuyItemAction {
    public BuyBroadswordAction() {

    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +"purchases the Broad Sword" ;
    }

    /**
     * Perform the Action.Give player the weapon when the transaction is successful
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.asSoul().subtractSouls(500)== true){
            BroadSword broadSword=new BroadSword();
            SwapWeaponAction swapWeaponAction=new SwapWeaponAction(broadSword);
             swapWeaponAction.execute(actor, map);
            return "Purchased BroadSword";
        }
        return "Purchased Failed";
    }
}
