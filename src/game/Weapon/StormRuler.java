package game.Weapon;


public class StormRuler extends Sword {
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public StormRuler(String name, char displayChar, int damage, String verb, int hitRate,String swordPassiveSkill) {
        super(name, displayChar, damage, verb, hitRate, swordPassiveSkill);
        this.name = name;
        this.displayChar = displayChar;
        this.damage = damage;
        this.verb = verb;
        this.hitRate = hitRate;
        //this.swordPassiveSkill=swordPassiveSkill;
    }

    int chargeCounter=0;

    public String Charge(){

        if (chargeCounter==3){
            return "Charge 3/3 Finish!";
        }else {
            chargeCounter++;
            return "StormRuler Charged"+chargeCounter+"/3";
        }
    }

    public void windSLash(){
        // Stun Boss


        //Double Damage
        //Get Damage--> Multiply-->return

    }

}
