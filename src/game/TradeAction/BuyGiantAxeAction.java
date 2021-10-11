package game.TradeAction;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.SwapWeaponAction;
import game.Weapon.GiantAxe;

public class BuyGiantAxeAction extends BuyItemAction{
    public BuyGiantAxeAction() {

    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +"purchases the Giant Axe";
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
        if(actor.asSoul().subtractSouls(1000)==true){
            GiantAxe giantAxe=new GiantAxe();
            SwapWeaponAction swapWeaponAction=new SwapWeaponAction(giantAxe);
             swapWeaponAction.execute(actor, map);
        return "Purchased Giant Axe";
        }
        return "Purchase Failed";
    }
}