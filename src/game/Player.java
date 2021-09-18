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
	
	private int souls = 0;

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
		this.addItemToInventory(new Gun());	  //TODO: Change to broad sword
		registerInstance();
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		message();
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
		
		ResetManager manager = ResetManager.getInstance(); // Call soft reset
		manager.run();
		placeTokenOfSoul(map, lastAction); // Place the token of soul

		// Soft reset the player
		// TODO: move to bonfire
		if(this.hasCapability(Status.SOFTRESET)) {
			this.heal(Integer.MAX_VALUE);
			// Heal the player twice because the player may hurt before falling valley, in this case
			// one heal cannot get to the maximum.
			this.heal(maxHitPoints);
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
	
//	@Override
//	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//		Actions actions = new Actions();
//		// it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//		return actions;
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
	
	public void message() {
		String message;
		String weapon = ", (no weapon)";
		if(!(this.getWeapon() instanceof IntrinsicWeapon))
			weapon = ", (holding " + this.getWeapon().toString()+")";
		message = name + "(" + hitPoints + "/" + maxHitPoints + ")" + weapon + ", " + souls +" souls";
		System.out.println(message);
	}
	
	@Override
	public String toString() {
//		String message = "(no weapon)";
//		if(!(this.getWeapon() instanceof IntrinsicWeapon))
//			message = "(holding " + this.getWeapon().toString()+")";
//		return name + "(" + hitPoints + "/" + maxHitPoints + ")" + message + ", " + souls +" souls";
		return name;
	}

	public void placeTokenOfSoul(GameMap map, Action lastAction) {
		TokenOfSoul token = new TokenOfSoul();
		this.asSoul().transferSouls(token);
		if(map.locationOf(this).getGround().getDisplayChar()!='+')
			map.locationOf(this).addItem(token);
		else {
			int x = map.locationOf(this).x();
			int y = map.locationOf(this).y();
			if(lastAction.hotkey()=="8")
				y++;
			else if(lastAction.hotkey()=="2")
				y--;
			else if(lastAction.hotkey()=="4")
				x++;
			else if(lastAction.hotkey()=="6")
				x--;
			else if(lastAction.hotkey()=="7") {
				x++;
				y++;
			}
			else if(lastAction.hotkey()=="3") {
				x--;
				y--;
			}
			else if(lastAction.hotkey()=="9") {
				x--;
				y++;
			}
			else if(lastAction.hotkey()=="1") {
				x++;
				y--;
			}
			map.at(x, y).addItem(token);
		}
	}
}
