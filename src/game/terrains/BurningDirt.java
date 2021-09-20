package game.terrains;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class BurningDirt extends Ground {
    /**
     * Ground can also experience the joy of time.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Actor player = location.getActor();
        int burnRound=0;
        burnRound++;
        if (burnRound<=3) {
            if (player != null) {
                player.hurt(25);

            }
        }
        else {
            location.setGround(new Dirt());

        }
    }


    public BurningDirt() {
        super('H');

    }
}
