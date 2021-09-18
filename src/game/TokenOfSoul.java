package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpSoulAction;
import game.enums.Status;
import game.interfaces.Resettable;
import game.interfaces.Soul;

public class TokenOfSoul extends Item implements Soul, Resettable{

	public TokenOfSoul() {
		super("Token of Souls", '$', false);
		allowableActions.add(new PickUpSoulAction(this));
		registerInstance();
	}
	
	@Override
	public List<Action> getAllowableActions() {
		return allowableActions.getUnmodifiableActionList();
	}

	@Override
	public void transferSouls(Soul soulObject) {
		// TODO Auto-generated method stub
	}

	@Override
	public void resetInstance() {
		// TODO Auto-generated method stub
		this.addCapability(Status.SOFTRESET);
	}
	
	@Override
	public void tick(Location currentLocation) {
		if(this.hasCapability(Status.SOFTRESET))
			currentLocation.removeItem(this);
	}

	@Override
	public boolean isExist() {
		// TODO Auto-generated method stub
		return false;
	}

}
