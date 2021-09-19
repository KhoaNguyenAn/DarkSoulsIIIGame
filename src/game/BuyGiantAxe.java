package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Weapon.GiantAxe;

public class BuyGiantAxe extends BuyItemAction{
    public BuyGiantAxe() {

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
        if(subtractSouls(1000)==true){
            GiantAxe giantAxe=new GiantAxe("GiantAxe",'G',50,"hit",80);
            SwapWeaponAction swapWeaponAction=new SwapWeaponAction(giantAxe);
//             swapWeaponAction.execute(player,gameMap.);
        return "Purchased Giant Axe";
        }
        return "Purchase Failed";
    }
}