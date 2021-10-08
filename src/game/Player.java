package game;

import edu.monash.fit2099.engine.*;
import game.Weapon.BroadSword;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul, Resettable {
	/**
	 * The number of souls to reward after enemies was defeated
	 */
	private SoulsManager souls;
	private Location bonfireLocation;

	private final Menu menu = new Menu();
//	private EstusFlask estusFlask;
//	private BroadSword broadSword;
	/**
	 * Increase current maximum hp. Current hitPoints becomes new maxHitPoints.
	 *
	 * @param points modifier points
	 */
	@Override
	public void increaseMaxHp(int points) {
		super.increaseMaxHp(points);
	}

	/**
	 * Reduce current maximum hp. Current hitPoints becomes new maxHitPoints.
	 * The minimum maxHitPoints is 1 HP.
	 *
	 * @param points modifier points
	 */
	@Override
	public void decreaseMaxHp(int points) {
		super.decreaseMaxHp(points);
	}

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints, Location bonfire) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);	// To distinguish between enemies and player
		this.addCapability(Abilities.REST);		// Ability to rest on bonfire
		this.addCapability(Abilities.FALL);		// Ability to fall from valley
		this.addCapability(Abilities.BUY_ITEMS); // Ability to buy items from Vendor
		this.addCapability(Abilities.ENTER);	// Ability to enter the floor
		this.addCapability(Abilities.PLAYER); // Player will not be removed from map after dead.
		this.addItemToInventory(new BroadSword());	  //
		this.addItemToInventory(new EstusFlask(this.maxHitPoints));
		this.souls = new SoulsManager(5000);	// Use SoulsManager to handle/store souls
		this.bonfireLocation = bonfire;
		registerInstance();		// Register to reset list

	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		message();		// Display the current status of player
		// Check if player is conscious or not.
		if (!this.isConscious()) {
			// Display the dead message
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
			// Play dead, run soft reset
			ResetManager manager = ResetManager.getInstance();
			manager.run();
			placeTokenOfSoul(map, lastAction); // Place the token of soul
	
			// Soft reset the player
			if(this.hasCapability(Status.SOFTRESET)) {
				this.heal(Integer.MAX_VALUE);
				// Heal the player twice because the player may hurt before falling valley, in this case
				// one heal cannot get to the maximum.
				this.heal(maxHitPoints);
				map.moveActor(this, map.at(this.bonfireLocation.x(), this.bonfireLocation.y()));
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
	/**
	 * Override the transferSouls so that the souls of player can be transferred to token of souls
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(souls.getSouls());
		souls.clear();
	}

//	public void buyitems(){
//		//reference to player
//		//Player
//	}

	/**
	 * Override the addSouls so that player can gain souls
	 */
	@Override
	public boolean addSouls(int souls){
		this.souls.add(souls);
		return true;
	}
	/**
	 * Override subtractSouls so that it can deduce the player's souls (can be used in purchasing)
	 */
	@Override
	public boolean subtractSouls(int souls){
		boolean res = false;
		res = this.souls.sub(souls);
		return res;
	}
	/**
	 * Add SOFTRESET status for soft reset.
	 */
	@Override
	public void resetInstance() {
		this.addCapability(Status.SOFTRESET);
	}
	/**
	 * The player will not be removed after soft reset, so return true
	 */
	@Override
	public boolean isExist() {
		return true;
	}
	/**
	 * Method to display current status of player
	 */
	public void message() {
		String message;
		String weapon = ", (no weapon)";
		if(!(this.getWeapon() instanceof IntrinsicWeapon))
			weapon = ", (holding " + this.getWeapon().toString()+")";
		message = name + "(" + hitPoints + "/" + maxHitPoints + ")" + weapon + ", " + souls.getSouls() +" souls";
		System.out.println(message);
	}
	
	/**
	 * Method to place the token of soul
	 * @param map		GameMap object
	 * @param lastAction		Action object, the last action done by player
	 */
	public void placeTokenOfSoul(GameMap map, Action lastAction) {
		TokenOfSoul token = new TokenOfSoul();
		this.asSoul().transferSouls(token);
		// If player died in valley, find the step behind and place the token of souls
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

	public Location getBonfireLocation() {
		return this.bonfireLocation;
	}
}
