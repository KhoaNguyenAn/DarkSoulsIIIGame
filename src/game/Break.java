package game;

import edu.monash.fit2099.engine.*;
import game.enums.Status;
public class Break extends WeaponAction{
	protected Actor target;
	public Break(WeaponItem weaponItem, Actor target) {
		super(weaponItem);
		this.target = target;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		if(actor.hasCapability(Status.ACTIVE)) {
			int damage = this.weapon.damage()/2;
			String result = actor + " " + "Slash" + " " + target + " for " + damage + " hitpoints.";
			target.hurt(damage);
			return result;
		}
		
		else
			actor.addCapability(Status.ACTIVE);
		return menuDescription(actor);
		
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " enrages the " + this.weapon.toString();
	}

}
