package game;

import edu.monash.fit2099.engine.Item;
import game.interfaces.Resettable;

public class EstusFlask extends Item implements Resettable {
    
    private int charges;
    private final int MAX_CHARGES;
    private int healingPercentage;

    public EstusFlask() {
		super("EstusFlask", 'o', false);
        this.MAX_CHARGES = 3;
        this.charges = MAX_CHARGES;
        this.healingPercentage = 40;
        allowableActions.add(new Drink());
	}

    public void heal(Player currentPlayer) {
        if (charges > 0) {
            int healPoint = currentPlayer.getMaxHitPoints() / 100 * healingPercentage;
            currentPlayer.heal(healPoint);
            charges -= 1;
        }
        else {
            System.out.println("Can not heal");
        }
    }

    public void displayCharges(Player currentPlayer) {
        System.out.println(currentPlayer.getName() + " drinks Estus Flask (" + this.charges +"/3)");
    }

    @Override
    public void resetInstance() {
        // TODO Auto-generated method stub
        this.charges = MAX_CHARGES;
    }

    @Override
    public boolean isExist() {
        // TODO Auto-generated method stub
        return false;
    }

}
