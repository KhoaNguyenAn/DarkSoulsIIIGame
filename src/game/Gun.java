package game;
import edu.monash.fit2099.engine.*;
public class Gun extends WeaponItem{

	public Gun() {
		super("Dz's Gun", '?', 80, "Shoot", 100);
		// TODO Auto-generated constructor stub
	}
	@Override
	public WeaponAction getActiveSkill(Actor target, String direction){
		return new CritShoot(this, target);
	}
}
