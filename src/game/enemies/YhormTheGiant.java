package game.enemies;

import game.Weapon.YhormsGiantMachete;
import game.enums.Abilities;
/**
 * Class representing Yhorm the giant
 * @author Dongzheng Wu
 */
public class YhormTheGiant extends LordOfCinder{
	/**
	 * Constructor of class
	 */
	public YhormTheGiant() {
		super("Yhorm the Giant", 'Y', 500);
		this.addItemToInventory(new YhormsGiantMachete(this));
		this.addCapability(Abilities.YHORM);	// For unique behaviour
	}

}
