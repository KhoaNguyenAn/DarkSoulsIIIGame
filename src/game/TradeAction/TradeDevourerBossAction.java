package game.TradeAction;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.SwapWeaponAction;
import game.Weapon.DarkmoonLongbow;

public class TradeDevourerBossAction extends BuyItemAction {
    public TradeDevourerBossAction() {

    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "trades Lord of Cinder from  Aldrich the Devourer and gets its weapon";
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

        for (Item item : actor.getInventory()) {
            String a = item.toString();
            if (item.toString().equals("Aldrich the Devourer's Cinders of a Lord")) {
                actor.removeItemFromInventory(item);
                DarkmoonLongbow darkmoonLongbow = new DarkmoonLongbow();
                SwapWeaponAction swapWeaponAction = new SwapWeaponAction(darkmoonLongbow);
                swapWeaponAction.execute(actor, map);
                return "Traded Aldrich the Devourer Lord of Cinder";
            }

        }
        return "Kill the Boss first Bro";
    }
}
