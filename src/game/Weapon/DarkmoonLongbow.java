package game.Weapon;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.enums.*;
import game.skills.RangeAttack;

import java.util.ArrayList;
import java.util.List;

import static game.enums.Abilities.RANGE_ATTACK;

public class DarkmoonLongbow extends Bow {


    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public DarkmoonLongbow(String name, char displayChar, int damage, String verb, int hitRate, String swordPassiveSkill) {
        super("DarkmoonLongbow", ')',70, "shoot", 80,"critical Strike");




    }
    public DarkmoonLongbow(){
        super("DarkmoonLongbow", ')', 70, "shoot", 80, "critical Strike");
    }

    /**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     */
    @Override
    public int damage() {
        int newDamage = criticalStrike();
        return newDamage;
    }

    /**
     * Inform a carried Item of the passage of time.
     * <p>
     * This method is called once per turn, if the Item is being carried.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        actor.addCapability(RANGE_ATTACK);
        int X = currentLocation.x() - 3;
        int Y = currentLocation.y() - 3;
        GameMap map = currentLocation.map();
        allowableActions.clear();
        List<Action> actions = new ArrayList<>();
        List<Location> locationList = new ArrayList<>();
        for (int y = currentLocation.y() - 3; y <= currentLocation.y() + 3; y++) {
            for (int x = currentLocation.x() - 3; x <= currentLocation.x() + 3; x++) {

                Location temp = new Location(map, x, y);

                if (temp.getActor() != null && temp.getActor() != actor && !temp.getActor().hasCapability(Status.VENDOR)) {
                    locationList.add(temp);
                    actions.add(new RangeAttack(temp.getActor(), "in a range of 3 blocks"));

                }
            }
        }
        allowableActions.add(actions);

    }


    /**
     * Returns the chance to hit the target in integer. Use it altogether with nextInt() method.
     *
     * @return Integer, such as
     */
    @Override
    public int chanceToHit() {


        return super.chanceToHit();
    }

    /**
     * Getter.
     * <p>
     * Returns an unmodifiable copy of the actions list so that calling methods won't
     * be able to change what this Item can do without the Item checking.
     *
     * @return an unmodifiable list of Actions
     */
    @Override
    public List<Action> getAllowableActions() {
        return super.getAllowableActions();
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }


}

