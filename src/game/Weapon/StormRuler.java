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
        super("StormRuler", '7', 50, "blow", 60, "critical strike, Dullness");

    }

    public StormRuler() {
        super("StormRuler", '7', 50, "blow", 60, "critical strike, Dullness");

    }
    /**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     */
    @Override
    public int damage() {
        int newDamage= criticalStrike();
        int finalDamage= Dullness(newDamage);
        return super.damage();
    }

    public int Dullness(int newDamage){

        //if enemy is not Yhorm's Giant
        return newDamage/2 ;
    }
}
