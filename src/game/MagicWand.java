package game;
import edu.monash.fit2099.engine.*;
public class MagicWand extends WeaponItem{

	public MagicWand() {
		super("Magic Wand", 'h', 1, "hit", 100);
		// TODO Auto-generated constructor stub
	}
	@Override
	public WeaponAction getActiveSkill(Actor target, String direction){
		return new Heal(this, target);
	}
}