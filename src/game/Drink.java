package game;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class Drink extends Action {
    private EstusFlask estusFlask;

    public Drink(EstusFlask estusFlask) {
        this.estusFlask = estusFlask;
    }
    public String execute(Actor actor, GameMap map) {
        if (this.estusFlask.getCharges() > 0) {
            int healPoint = this.estusFlask.getMaxHitPoints() / 100 * this.estusFlask.getHealingPercentage();
            actor.heal(healPoint);
            this.estusFlask.setCharges(this.estusFlask.getCharges() - 1); 
            displayCharges(actor);
            return menuDescription(actor);
        }
        else {
            return "Can't heal !";
        }
    }
  	public String menuDescription(Actor actor) {
        return "Drink Estus Flask";
    }
 
    public void displayCharges(Actor currentPlayer) {
        System.out.println(currentPlayer.toString() + " drinks Estus Flask (" + this.estusFlask.getCharges() +"/3)");
    }
}
