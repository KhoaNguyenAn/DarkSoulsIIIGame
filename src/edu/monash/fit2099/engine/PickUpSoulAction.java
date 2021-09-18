package edu.monash.fit2099.engine;

import game.TokenOfSoul;

public class PickUpSoulAction extends Action{

	protected TokenOfSoul item;
	
	public PickUpSoulAction(TokenOfSoul item) {
		this.item = item;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		map.locationOf(actor).removeItem(item);
		item.transferSouls(actor.asSoul());
		return menuDescription(actor);
	}

	/**
	 * Describe the action in a format suitable for displaying in the menu.
	 *
	 * @see Action#menuDescription(Actor)
	 * @param actor The actor performing the action.
	 * @return a string, e.g. "Player picks up the rock"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " picks up the " + item;
	}
	
}
