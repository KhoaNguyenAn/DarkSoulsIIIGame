package game.skills;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
import game.interfaces.Soul;

import java.util.Random;

public class RangeAttack extends Action {

    private Actor target;
    private String direction;
    /**
     * Random number generator
     */
    protected Random rand = new Random();

    public RangeAttack(Actor target, String direction) {

        this.target = target;
        this.direction = direction;
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
        String result = new String();
        Boolean wall_detected = false;
        Weapon weapon = actor.getWeapon();
//        if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
//            return actor + " misses " + target + ".";
//        }
        if (!map.contains(target) || !map.contains(actor))
            return null;
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        NumberRange xs, ys;
//        if (here.x() == there.x() || here.y() == there.y()) {
            xs = new NumberRange(Math.min(here.x(), there.x()), Math.abs(here.x() - there.x()) + 1);
            ys = new NumberRange(Math.min(here.y(), there.y()), Math.abs(here.y() - there.y()) + 1);

            for (int x : xs) {
                for (int y : ys) {
                    if (map.at(x, y).getGround().blocksThrownObjects()) {
                        wall_detected = true;

                    }
                }
            }

            if (wall_detected == false) {
                int damage = weapon.damage();
                result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
                target.hurt(damage);
                // If target is killed  TODO: all active skills should include this checking to allowed rewarding souls
                if (!target.isConscious()) {
                    Actions dropActions = new Actions();
                    // Check if the target has revive ability or not.
                    if (!(target.hasCapability(Abilities.REVIVE))) {
                        // In our design, we allow player to drop all the items after dead
                        if (target.hasCapability(Abilities.PLAYER)) {
                            for (Item item : target.getInventory())
                                dropActions.add(item.getDropAction(actor));
                            for (Action drop : dropActions)
                                drop.execute(target, map);
                        }
                        // If target is enemy reward souls
                        if (!(target.hasCapability(Abilities.PLAYER))) {
                            target.asSoul().transferSouls(actor.asSoul());        // After defeat enemy, gain souls
                        }
                    } else {
                        // If target has abilities to revive, give it extra turn to revive.
                        if (target.hasCapability(Abilities.REVIVE)) {
                            Soul souls = target.asSoul();
                            target.playTurn(null, getNextAction(), map, null);
                            // If didn't revive, drop the items, remove it and reward souls.
                            if (!target.isConscious()) {
                                souls.transferSouls(actor.asSoul());
                                result += System.lineSeparator() + target + " is killed !";
                            } else
                                result += System.lineSeparator() + target + " is revived !";
                        }
                    }
                }
            } else {
                result += "The Wall BLocked the attack from " + actor + " to " + target;
            }
//        }
        return result;
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + " shoots " + target + " at " + direction;
    }
}

