package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Menu;
import edu.monash.fit2099.engine.Location;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {

	private final Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.FALL);
		this.addCapability(Abilities.ENTER);
		this.addCapability(Abilities.REVIVE); //player will not be removed from map after dead.
		this.addItemToInventory(new Gun());
		registerInstance();
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
	
		if (!this.isConscious()) {
			System.out.println("                                                                                                                               \n"
					+ "                                                                                                                       dddddddd\n"
					+ "YYYYYYY       YYYYYYY                                     DDDDDDDDDDDDD          iiii                                  d::::::d\n"
					+ "Y:::::Y       Y:::::Y                                     D::::::::::::DDD      i::::i                                 d::::::d\n"
					+ "Y:::::Y       Y:::::Y                                     D:::::::::::::::DD     iiii                                  d::::::d\n"
					+ "Y::::::Y     Y::::::Y                                     DDD:::::DDDDD:::::D                                          d:::::d \n"
					+ "YYY:::::Y   Y:::::YYYooooooooooo   uuuuuu    uuuuuu         D:::::D    D:::::D iiiiiii     eeeeeeeeeeee        ddddddddd:::::d \n"
					+ "   Y:::::Y Y:::::Y oo:::::::::::oo u::::u    u::::u         D:::::D     D:::::Di:::::i   ee::::::::::::ee    dd::::::::::::::d \n"
					+ "    Y:::::Y:::::Y o:::::::::::::::ou::::u    u::::u         D:::::D     D:::::D i::::i  e::::::eeeee:::::ee d::::::::::::::::d \n"
					+ "     Y:::::::::Y  o:::::ooooo:::::ou::::u    u::::u         D:::::D     D:::::D i::::i e::::::e     e:::::ed:::::::ddddd:::::d \n"
					+ "      Y:::::::Y   o::::o     o::::ou::::u    u::::u         D:::::D     D:::::D i::::i e:::::::eeeee::::::ed::::::d    d:::::d \n"
					+ "       Y:::::Y    o::::o     o::::ou::::u    u::::u         D:::::D     D:::::D i::::i e:::::::::::::::::e d:::::d     d:::::d \n"
					+ "       Y:::::Y    o::::o     o::::ou::::u    u::::u         D:::::D     D:::::D i::::i e::::::eeeeeeeeeee  d:::::d     d:::::d \n"
					+ "       Y:::::Y    o::::o     o::::ou:::::uuuu:::::u         D:::::D    D:::::D  i::::i e:::::::e           d:::::d     d:::::d \n"
					+ "       Y:::::Y    o:::::ooooo:::::ou:::::::::::::::uu     DDD:::::DDDDD:::::D  i::::::ie::::::::e          d::::::ddddd::::::dd\n"
					+ "    YYYY:::::YYYY o:::::::::::::::o u:::::::::::::::u     D:::::::::::::::DD   i::::::i e::::::::eeeeeeee   d:::::::::::::::::d\n"
					+ "    Y:::::::::::Y  oo:::::::::::oo   uu::::::::uu:::u     D::::::::::::DDD     i::::::i  ee:::::::::::::e    d:::::::::ddd::::d\n"
					+ "    YYYYYYYYYYYYY    ooooooooooo       uuuuuuuu  uuuu     DDDDDDDDDDDDD        iiiiiiii    eeeeeeeeeeeeee     ddddddddd   ddddd\n"
					+ "                                                                                                                               ");
		// Call soft rest
		ResetManager manager = ResetManager.getInstance();
		manager.run();
		// Soft reset the player
		// TODO: move to bonfire
		if(this.hasCapability(Status.SOFTRESET)) {
			this.heal(Integer.MAX_VALUE);
			map.moveActor(this, map.at(38, 12));
			this.removeCapability(Status.SOFTRESET);
		}
		return new DoNothingAction();
		}
		
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void transferSouls(Soul soulObject) {
		//TODO: transfer Player's souls to another Soul's instance.
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
		return actions;
	}
	
//	//TODO: This method only used for testing, should be removed after finished.
//	@Override
//	protected IntrinsicWeapon getIntrinsicWeapon() {
//		return new IntrinsicWeapon(80, "shoot");
//	}

	@Override
	public void resetInstance() {
		// TODO Auto-generated method stub
		this.addCapability(Status.SOFTRESET);
		
	}

	@Override
	public boolean isExist() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String toString() {
		String message = "(no weapon)";
		if(!(this.getWeapon() instanceof IntrinsicWeapon))
			message = "(holding " + this.getWeapon().toString()+")";
		return name + "(" + hitPoints + "/" + maxHitPoints + ")" + message;
	}
}
