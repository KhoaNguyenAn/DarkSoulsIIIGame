package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class WindSlashAction extends Action {

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
     * Constructor.
     *
     * @param target the Actor to attack
     */
    public WindSlashAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }


    @Override
    public String execute(Actor actor, GameMap map) {

        Weapon weapon = actor.getWeapon();


        int damage = weapon.damage()*2;
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
//        DoNothingAction doNothingAction=new DoNothingAction();
//        doNothingAction.execute(target,map);
        //Stun Target target.
//        actor.playTurn()
        if (!target.isConscious()) {
            Actions dropActions = new Actions();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            //TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
            map.removeActor(target);
            result += System.lineSeparator() + target + " is killed.";
        }

        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction;
    }
}
