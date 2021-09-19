package game;
import edu.monash.fit2099.engine.GameMap;

public class Bonfire {


    public void chooseToRest(Player player, GameMap map) {
        Rest restAction = new Rest();
        restAction.execute(player, map);
    }
}
