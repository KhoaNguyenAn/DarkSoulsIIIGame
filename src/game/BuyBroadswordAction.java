package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Weapon.BroadSword;

public class BuyBroadswordAction extends BuyItemAction{
    public BuyBroadswordAction() {

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
        if(subtractSouls(500)==true){
            BroadSword broadSword=new BroadSword("BroadSword",'q',30,"slash",80,"Critical Strike");
            SwapWeaponAction swapWeaponAction=new SwapWeaponAction(broadSword);
//             swapWeaponAction.execute(player,gameMap.);
            return "Purchased BroadSword";
        }
        return "Purchased Failed";
    }
}
