package game;


import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.enums.Status;

import java.util.ArrayList;
import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class SpinAttackAction extends WeaponAction {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

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
    public SpinAttackAction(WeaponItem weaponItem ) {
        super(weaponItem);

    }




    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();




        int damage = weapon.damage()/2;
        String result = actor + " spin attacks at all directions" + " for " + damage + " damage.";
        ArrayList <Actor> targetList = new ArrayList<Actor>();
        // To get the target from adjacent
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if(destination.getActor() != null) {
                   targetList.add( destination.getActor());
            }
        }

        //player
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            targetList.remove(actor);
            for (int i = 0; i < targetList.size(); i++) {
                targetList.get(i).hurt(damage);
            }
        }
        if (!actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            for (int i = 0; i < targetList.size(); i++) {
                if (targetList.get(i).hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    targetList.get(i).hurt(damage);
                }
            }
        }


       for (int i=0;i<targetList.size();i++){
            if (!targetList.get(i).isConscious()) {
                Actions dropActions = new Actions();
                // drop all items
                for (Item item : targetList.get(i).getInventory())
                    dropActions.add(item.getDropAction(actor));
                for (Action drop : dropActions)
                    drop.execute(targetList.get(i), map);
                // remove actor
                //TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
                if (!targetList.get(i).hasCapability(Abilities.PLAYER))
                    map.removeActor(targetList.get(i));
                result += System.lineSeparator() + targetList.get(i) + " is killed.";
                return result;
            }
        }
        
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " spin attacks everyone near him at all directions ";
    }
}
