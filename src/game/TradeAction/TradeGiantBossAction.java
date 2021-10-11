package game.TradeAction;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.SwapWeaponAction;
import game.Weapon.YhormsGiantMachete;

public class TradeGiantBossAction extends BuyItemAction {
    public TradeGiantBossAction() {

    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +"trades Lord of Cinder from Yhorms the Giant and gets its weapon" ;
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
            if (item.toString().equals("Yhorm the Giant's Cinder of a Lord")) {
                actor.removeItemFromInventory(item);
                YhormsGiantMachete yhormsGiantMachete = new YhormsGiantMachete();
                SwapWeaponAction swapWeaponAction = new SwapWeaponAction(yhormsGiantMachete);
                swapWeaponAction.execute(actor, map);
                return "Traded Yhorm the Giant Lord of Cinder";
            }

        }
        return "Kill the Boss first Bro";
    }
}
