package game.enemies;

import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.KilledAction;
import game.PortableItem;
import game.StunAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.UniqueBehaviour;
import game.enums.Abilities;
import game.enums.Status;
import game.interfaces.Behaviour;
import game.interfaces.Resettable;

/**
 * The boss of Design o' Souls
 * FIXED: Features are implemented.
 * TODO: Could it be an abstract class? If so, why and how? (Answered in design rationale)
 * LordOfCinder represents a lord of cinder (boss)
 */
public abstract class LordOfCinder extends Enemies implements Resettable{
	/**
	 * Location is used to store the initial position of load of cinder
	 */
	private Location location = null;
    /**
     * Constructor, it is created in application, Worth 5000 souls.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, 5000);
        this.addCapability(Abilities.BOSS);		// Used for some boss feature.
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action	 the action this enemy going to do
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
    	// If Boss is defeated
    	if(!this.isConscious()) {
    		isKilled(this, map);
    		return new KilledAction();
    	}
    	// Add location of Lord of cinder
    	if (location == null)
			location = map.locationOf(this);
    	// If stunned, do nothing and shows the stunned status, only used for Yhorm the gaint
    	if(this.hasCapability(Status.STUNNED) && this.hasCapability(Abilities.YHORM)) {
    		this.removeCapability(Status.STUNNED);
    		return new StunAction();
    	}
    	// If soft reset is triggered, move this enemy to original position, heal it, remove status.
    	if(this.hasCapability(Status.SOFTRESET)) {
			map.moveActor(this, location);
			this.removeCapability(Status.SOFTRESET);
			this.heal(maxHitPoints);
			for (int i = 0; i < behaviours.size(); i++) {
	        	if(behaviours.get(i) instanceof FollowBehaviour)
	        		behaviours.remove(i);
	        }
			return new DoNothingAction();
		}
        for(Behaviour Behaviour : behaviours) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
    	return new DoNothingAction();
    }
    /**
     * Override getAllowableActions to achieve unique behaivour feature
     * @see edu.monash.fit2099.engine.Actor#getAllowableActions(Actor otherActor, String direction, GameMap map) 
     */
    @Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions actions = new Actions();
		
		// HOSTLE_TO_ENEMY status avoid enemies attack each other
		if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
			
			// Allow player to attack this enemy
			actions.add(new AttackAction(this,direction));
			
			// Allow player to use weapon skill to this enemy if they had
			if(!(otherActor.getWeapon() instanceof IntrinsicWeapon)) {
				actions.add(otherActor.getWeapon().getActiveSkill(this, direction));
			}
			
			// Add follow, attack and unique behaviour to lord of cinder
			behaviours.add(new FollowBehaviour(otherActor));
			behaviours.add(0, new AttackBehaviour());
			behaviours.add(0, new UniqueBehaviour(hitPoints, maxHitPoints));
		}
		return actions;
	}

    public void isKilled(Actor actor, GameMap map){
    	// Drop the cinder of lord
    	map.locationOf(actor).addItem(new PortableItem(name + "'s Cinders of a Lord", '%'));
    	// Display the message
    	System.out.println("                                                                                                                                                                                                                                                                                                                                                                                                            \n"
    			+ "                                                                                                                                                                                                                                                                                                                                                                                                            \n"
    			+ "LLLLLLLLLLL                  OOOOOOOOO     RRRRRRRRRRRRRRRRR   DDDDDDDDDDDDD                  OOOOOOOOO     FFFFFFFFFFFFFFFFFFFFFF             CCCCCCCCCCCCCIIIIIIIIIINNNNNNNN        NNNNNNNNDDDDDDDDDDDDD      EEEEEEEEEEEEEEEEEEEEEERRRRRRRRRRRRRRRRR        FFFFFFFFFFFFFFFFFFFFFF      AAA               LLLLLLLLLLL             LLLLLLLLLLL             EEEEEEEEEEEEEEEEEEEEEENNNNNNNN        NNNNNNNN\n"
    			+ "L:::::::::L                OO:::::::::OO   R::::::::::::::::R  D::::::::::::DDD             OO:::::::::OO   F::::::::::::::::::::F          CCC::::::::::::CI::::::::IN:::::::N       N::::::ND::::::::::::DDD   E::::::::::::::::::::ER::::::::::::::::R       F::::::::::::::::::::F     A:::A              L:::::::::L             L:::::::::L             E::::::::::::::::::::EN:::::::N       N::::::N\n"
    			+ "L:::::::::L              OO:::::::::::::OO R::::::RRRRRR:::::R D:::::::::::::::DD         OO:::::::::::::OO F::::::::::::::::::::F        CC:::::::::::::::CI::::::::IN::::::::N      N::::::ND:::::::::::::::DD E::::::::::::::::::::ER::::::RRRRRR:::::R      F::::::::::::::::::::F    A:::::A             L:::::::::L             L:::::::::L             E::::::::::::::::::::EN::::::::N      N::::::N\n"
    			+ "LL:::::::LL             O:::::::OOO:::::::ORR:::::R     R:::::RDDD:::::DDDDD:::::D       O:::::::OOO:::::::OFF::::::FFFFFFFFF::::F       C:::::CCCCCCCC::::CII::::::IIN:::::::::N     N::::::NDDD:::::DDDDD:::::DEE::::::EEEEEEEEE::::ERR:::::R     R:::::R     FF::::::FFFFFFFFF::::F   A:::::::A            LL:::::::LL             LL:::::::LL             EE::::::EEEEEEEEE::::EN:::::::::N     N::::::N\n"
    			+ "  L:::::L               O::::::O   O::::::O  R::::R     R:::::R  D:::::D    D:::::D      O::::::O   O::::::O  F:::::F       FFFFFF      C:::::C       CCCCCC  I::::I  N::::::::::N    N::::::N  D:::::D    D:::::D E:::::E       EEEEEE  R::::R     R:::::R       F:::::F       FFFFFF  A:::::::::A             L:::::L                 L:::::L                 E:::::E       EEEEEEN::::::::::N    N::::::N\n"
    			+ "  L:::::L               O:::::O     O:::::O  R::::R     R:::::R  D:::::D     D:::::D     O:::::O     O:::::O  F:::::F                  C:::::C                I::::I  N:::::::::::N   N::::::N  D:::::D     D:::::DE:::::E               R::::R     R:::::R       F:::::F              A:::::A:::::A            L:::::L                 L:::::L                 E:::::E             N:::::::::::N   N::::::N\n"
    			+ "  L:::::L               O:::::O     O:::::O  R::::RRRRRR:::::R   D:::::D     D:::::D     O:::::O     O:::::O  F::::::FFFFFFFFFF        C:::::C                I::::I  N:::::::N::::N  N::::::N  D:::::D     D:::::DE::::::EEEEEEEEEE     R::::RRRRRR:::::R        F::::::FFFFFFFFFF   A:::::A A:::::A           L:::::L                 L:::::L                 E::::::EEEEEEEEEE   N:::::::N::::N  N::::::N\n"
    			+ "  L:::::L               O:::::O     O:::::O  R:::::::::::::RR    D:::::D     D:::::D     O:::::O     O:::::O  F:::::::::::::::F        C:::::C                I::::I  N::::::N N::::N N::::::N  D:::::D     D:::::DE:::::::::::::::E     R:::::::::::::RR         F:::::::::::::::F  A:::::A   A:::::A          L:::::L                 L:::::L                 E:::::::::::::::E   N::::::N N::::N N::::::N\n"
    			+ "  L:::::L               O:::::O     O:::::O  R::::RRRRRR:::::R   D:::::D     D:::::D     O:::::O     O:::::O  F:::::::::::::::F        C:::::C                I::::I  N::::::N  N::::N:::::::N  D:::::D     D:::::DE:::::::::::::::E     R::::RRRRRR:::::R        F:::::::::::::::F A:::::A     A:::::A         L:::::L                 L:::::L                 E:::::::::::::::E   N::::::N  N::::N:::::::N\n"
    			+ "  L:::::L               O:::::O     O:::::O  R::::R     R:::::R  D:::::D     D:::::D     O:::::O     O:::::O  F::::::FFFFFFFFFF        C:::::C                I::::I  N::::::N   N:::::::::::N  D:::::D     D:::::DE::::::EEEEEEEEEE     R::::R     R:::::R       F::::::FFFFFFFFFFA:::::AAAAAAAAA:::::A        L:::::L                 L:::::L                 E::::::EEEEEEEEEE   N::::::N   N:::::::::::N\n"
    			+ "  L:::::L               O:::::O     O:::::O  R::::R     R:::::R  D:::::D     D:::::D     O:::::O     O:::::O  F:::::F                  C:::::C                I::::I  N::::::N    N::::::::::N  D:::::D     D:::::DE:::::E               R::::R     R:::::R       F:::::F         A:::::::::::::::::::::A       L:::::L                 L:::::L                 E:::::E             N::::::N    N::::::::::N\n"
    			+ "  L:::::L         LLLLLLO::::::O   O::::::O  R::::R     R:::::R  D:::::D    D:::::D      O::::::O   O::::::O  F:::::F                   C:::::C       CCCCCC  I::::I  N::::::N     N:::::::::N  D:::::D    D:::::D E:::::E       EEEEEE  R::::R     R:::::R       F:::::F        A:::::AAAAAAAAAAAAA:::::A      L:::::L         LLLLLL  L:::::L         LLLLLL  E:::::E       EEEEEEN::::::N     N:::::::::N\n"
    			+ "LL:::::::LLLLLLLLL:::::LO:::::::OOO:::::::ORR:::::R     R:::::RDDD:::::DDDDD:::::D       O:::::::OOO:::::::OFF:::::::FF                  C:::::CCCCCCCC::::CII::::::IIN::::::N      N::::::::NDDD:::::DDDDD:::::DEE::::::EEEEEEEE:::::ERR:::::R     R:::::R     FF:::::::FF     A:::::A             A:::::A   LL:::::::LLLLLLLLL:::::LLL:::::::LLLLLLLLL:::::LEE::::::EEEEEEEE:::::EN::::::N      N::::::::N\n"
    			+ "L::::::::::::::::::::::L OO:::::::::::::OO R::::::R     R:::::RD:::::::::::::::DD         OO:::::::::::::OO F::::::::FF                   CC:::::::::::::::CI::::::::IN::::::N       N:::::::ND:::::::::::::::DD E::::::::::::::::::::ER::::::R     R:::::R     F::::::::FF    A:::::A               A:::::A  L::::::::::::::::::::::LL::::::::::::::::::::::LE::::::::::::::::::::EN::::::N       N:::::::N\n"
    			+ "L::::::::::::::::::::::L   OO:::::::::OO   R::::::R     R:::::RD::::::::::::DDD             OO:::::::::OO   F::::::::FF                     CCC::::::::::::CI::::::::IN::::::N        N::::::ND::::::::::::DDD   E::::::::::::::::::::ER::::::R     R:::::R     F::::::::FF   A:::::A                 A:::::A L::::::::::::::::::::::LL::::::::::::::::::::::LE::::::::::::::::::::EN::::::N        N::::::N\n"
    			+ "LLLLLLLLLLLLLLLLLLLLLLLL     OOOOOOOOO     RRRRRRRR     RRRRRRRDDDDDDDDDDDDD                  OOOOOOOOO     FFFFFFFFFFF                        CCCCCCCCCCCCCIIIIIIIIIINNNNNNNN         NNNNNNNDDDDDDDDDDDDD      EEEEEEEEEEEEEEEEEEEEEERRRRRRRR     RRRRRRR     FFFFFFFFFFF  AAAAAAA                   AAAAAAALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLEEEEEEEEEEEEEEEEEEEEEENNNNNNNN         NNNNNNN\n"
    			+ "                                                                                                                                                                                                                                                                                                                                                                                                            \n"
    			+ "                                                                                                                                                                                                                                                                                                                                                                                                            \n"
    			+ "");
    	// Remove this actor
        map.removeActor(actor);
    }
}
