package game;

import edu.monash.fit2099.engine.Item;
import game.interfaces.Resettable;

public class EstusFlask extends Item implements Resettable {

   private int charges;
   private final int MAX_CHARGES;
   private int healingPercentage;
   private int maxHitPoints;

   public EstusFlask(int maxHitPoints) {
		super("EstusFlask", 'o', false);
        this.MAX_CHARGES = 3;
        this.charges = MAX_CHARGES;
        this.healingPercentage = 40;
        this.maxHitPoints = maxHitPoints;
        allowableActions.add(new Drink(this));
        registerInstance();	
	}


    public int getCharges() {
        return this.charges;
    }

    public void setCharges(int charges) {
        this.charges = charges;
    }

    public int getMAX_CHARGES() {
        return this.MAX_CHARGES;
    }

    public int getMaxHitPoints() {
        return this.maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }


    public int getHealingPercentage() {
        return this.healingPercentage;
    }

    public void setHealingPercentage(int healingPercentage) {
        this.healingPercentage = healingPercentage;
    }
   

   @Override
   public void resetInstance() {
       // TODO Auto-generated method stub
       this.charges = MAX_CHARGES;
   }

   @Override
   public boolean isExist() {
       // TODO Auto-generated method stub
       return true;
   }

   
   
}
