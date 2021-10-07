package game.enemies;

import game.Weapon.BroadSword;
/**
 * Class representing Aldrich the devourer
 * @author Dongzheng Wu
 */
public class AldrichTheDevourer extends LordOfCinder{
	/**
	 * Constructor of class
	 */
	public AldrichTheDevourer() {
		super("Aldrich the Devourer", 'A', 350);
		this.addItemToInventory(new BroadSword());	//TODO: Need to be replaced with DarkmoonLongbow
	}

}
