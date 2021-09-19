package game.Weapon;

public class YhormsGiantMachete extends Axe {
    /**
     * Constructor.
     *
     * @param name        name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage      amount of damage this weapon does
     * @param verb        verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate     the probability/chance to hit the target.
     */
    public YhormsGiantMachete(String name, char displayChar, int damage, String verb, int hitRate) {
        super("YhormsGiantMachete", 'M', 95, "Beat", 60);
    }

    /**
     * Returns the chance to hit the target in integer. Use it altogether with nextInt() method.
     *
     * @return Integer, such as
     */
    @Override
    public int chanceToHit() {
        int newHitRate= EmberForm(hitRate);
        return newHitRate ;
    }

public int EmberForm(int hitRate){
        if (true){
            hitRate=hitRate+30;
            return hitRate;
        }return hitRate;
}
}
