package game.Weapon;

public class GiantAxe extends Axe {
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public GiantAxe(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);

    }

    /**
     * Accessor for damage done by this weapon.
     *
     * @return the damage
     */
//    @Override
//    public int damage() {
//        //if at adjacent angle(Spin Attack
//        if(){
//            int adjDamage=damage/2;
//            return adjDamage;
//        }
//        return super.damage();
//    }
}
