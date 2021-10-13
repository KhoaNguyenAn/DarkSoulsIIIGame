package game;

import edu.monash.fit2099.engine.Item;
import game.actions.Drink;
import game.interfaces.Resettable;
/**
 *  EstuskFlask - A health portion for Player
 */
public class EstusFlask extends Item implements Resettable {
   
   private int charges;
   private final int MAX_CHARGES;
   private int healingPercentage;
   private int maxHitPoints;

   /**
    * Letter "o"
    * @param maxHitPoints
    */
   public EstusFlask(int maxHitPoints) {
		super("EstusFlask", 'o', false);
        this.MAX_CHARGES = 3;
        this.charges = MAX_CHARGES;  // Initialize the charges equal to MAX_CHARGES
        this.healingPercentage = 40;
        this.maxHitPoints = maxHitPoints;
        allowableActions.add(new Drink(this));
        registerInstance();	
	}

    /**
     * Getter for charges
     * @return
     */
    public int getCharges() {
        return this.charges;
    }

    /**
     * Setter for charges
     * @param charges
     */
    public void setCharges(int charges) {
        this.charges = charges;
    }

    /**
     * Getter for MAX_CHARGES
     * @return
     */
    public int getMAX_CHARGES() {
        return this.MAX_CHARGES;
    }

    /**
     * Getter for MaxHitPoints
     */
    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    /**
     * Setter for set max hit points
     * @param maxHitPoints
     */
    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    /**
     * Getter for healing percentage
     * @return
     */
    public int getHealingPercentage() {
        return this.healingPercentage;
    }

    /**
     * Setter for healing percentage
     * @param healingPercentage
     */
    public void setHealingPercentage(int healingPercentage) {
        this.healingPercentage = healingPercentage;
    }
   
    /**
     *  The reset instance for SOFT-RESET
     */
   @Override
   public void resetInstance() {
       // TODO Auto-generated method stub
       this.charges = MAX_CHARGES;
   }

   /**
    *  Overide method isExist 
    */
   @Override
   public boolean isExist() {
       // TODO Auto-generated method stub
       return true;
   }

   
   
}
