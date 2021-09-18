package game;
import edu.monash.fit2099.engine.*;
public class HeavySword extends WeaponItem{

	public HeavySword() {
		super("HeavySword", '&', 10, "slash", 1);
		// TODO Auto-generated constructor stub
	}
	@Override
	public WeaponAction getActiveSkill(Actor target, String direction){
		return new Break(this, target);
	}
}