package game;

import edu.monash.fit2099.engine.*;

public class RangeAttack extends WeaponAction {

    private Actor target;
    private String direction;

    public RangeAttack(WeaponItem weaponItem) {
        super(weaponItem);
        this.target = target;
    }


    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        int currentDistance = distance(here, there);


        if (currentDistance <= 3) {
            //Check walls
            //Damage
            return actor + " attacks " + target + " at " + direction;
        }


        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " shoots " + target + " at " + direction;
    }
}

