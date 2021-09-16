package game;

import edu.monash.fit2099.engine.GameMap;
import game.Weapon.BroadSword;
import game.Weapon.GiantAxe;
import game.interfaces.Soul;

public class Trade implements Soul {
    public Trade() {

    }

    /**
     * Transfer current instance's souls to another Soul instance.
     *
     * @param soulObject a target souls.
     */
    @Override
    public void transferSouls(Soul soulObject) {

    }


    /**
     * Allow other classes to deduct the number of this instance's souls
     * By default, an instance cannot get its own souls subtracted.
     * You may override this method to conduct subtraction on current souls.
     *
     * @param souls number souls to be deducted
     * @return transaction status. True if subtraction successful, otherwise False.
     */
    @Override
    public boolean subtractSouls(int souls) {
        return Soul.super.subtractSouls(souls);
    }

    public void buyBroadSword(Player player, GameMap gameMap){
        if(subtractSouls(500)==true){
            BroadSword broadSword=new BroadSword("BroadSword",'q',30,"slash",80,"Critical Strike");
             SwapWeaponAction swapWeaponAction=new SwapWeaponAction(broadSword);
//             swapWeaponAction.execute(player,gameMap.);

        }

    }


    public void buyGiantAxe(){
        if(subtractSouls(1000)==true){
            GiantAxe giantAxe= new GiantAxe("GiantAxe",'A',70,"chop",60);
            SwapWeaponAction swapWeaponAction=new SwapWeaponAction(giantAxe);
            //swapWeaponAction.execute(Player,)

        }

    }

    public void buyMaxHp(){
        if (subtractSouls(200)==true){
//            Player p1= new Player;
//            p1.increaseMaxHp(25);
        }
    }
}
