package game;
import java.util.ArrayList;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;
public class Heal extends WeaponAction{
	protected Actor target;
	public Heal(WeaponItem weaponItem, Actor target) {
		super(weaponItem);
		this.target = target;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		int damage = this.weapon.damage()*10;
		String result = actor + " " + "HEAL" + " " + target + " for " + damage + " hitpoints.";
		target.heal(damage);

		return result;
		
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " Heal " + target;
	}

}
