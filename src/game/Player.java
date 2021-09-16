package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Soul;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Soul {

	private final Menu menu = new Menu();

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
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Abilities.REST);
		this.addCapability(Abilities.BUY_ITEMS);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
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
	public void buyitems(){
		//reference to player
		//Player
	}
}
