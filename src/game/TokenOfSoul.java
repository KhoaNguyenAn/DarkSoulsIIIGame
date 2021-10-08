package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;
/**
 * Class representing the token of soul.
 * @author Dongzheng Wu
 */
public class TokenOfSoul extends Item implements Soul, Resettable{
	/**
	 * Attribute of SoulsManager object that can store and handle the souls
	 */
	private SoulsManager souls;
	/**
	 * Constructor of TokenOfSoul with empty souls
	 */
	public TokenOfSoul() {
		// Token of soul cannot be placed in inventor or dropped, so it is improbable.
		super("Token of Souls", '$', false);
		// Add action allow player pick it and gain souls
		allowableActions.add(new PickUpSoulAction(this));
		souls = new SoulsManager();
		registerInstance();		// It can be reset after player died
	}
	/**
	 * Constructor of TokenOfSoul with certain souls
	 * @param soul  integer of number of souls in Token of Souls
	 */
	public TokenOfSoul(int soul) {
		// Token of soul cannot be placed in inventor or dropped, so it is improbable.
		super("Token of Souls", '$', false);
		// Add action allow player pick it and gain souls
		allowableActions.add(new PickUpSoulAction(this));
		souls = new SoulsManager();
		registerInstance();		// It can be reset after player died
		this.souls.add(soul);
	}
	/**
	 * Override transferSouls so it can give player the souls
	 */
	@Override
	public void transferSouls(Soul soulObject) {
		soulObject.addSouls(souls.getSouls());
		souls.clear();
	}
	/**
	 * Override addSouls method so it can gain souls from player
	 */
	@Override
	public boolean addSouls(int souls){
		this.souls.add(souls);
		return true;
	}
	/**
	 * Add SOFTRESET status so that it can be reset
	 */
	@Override
	public void resetInstance() {
		// TODO Auto-generated method stub
		this.addCapability(Status.SOFTRESET);
	}
	/**
	 * Remove it after reset
	 */
	@Override
	public void tick(Location currentLocation) {
		if(this.hasCapability(Status.SOFTRESET))
			currentLocation.removeItem(this);
	}
	/**
	 * After reset the token of soul will be removed, so return false
	 */
	@Override
	public boolean isExist() {
		return false;
	}
}
