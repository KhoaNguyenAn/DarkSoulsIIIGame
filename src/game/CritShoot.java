package game;
import java.util.ArrayList;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
public class CritShoot extends WeaponAction{
	protected Actor target;
	public CritShoot(WeaponItem weaponItem, Actor target) {
		super(weaponItem);
		this.target = target;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		int damage = this.weapon.damage()*20;
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			result += System.lineSeparator() + target + " is killed.";
		}
		return result;
		
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " Crit shoot " + target;
	}

}
