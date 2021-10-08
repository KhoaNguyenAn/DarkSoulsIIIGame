package game;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

import java.util.Random;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}



	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}
		
		// Get the damage message and hurt target
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		
		// If target is killed
		if (!target.isConscious()) {
			Actions dropActions = new Actions();
			
			// Check if the target is boss
			if(!target.hasCapability(Abilities.BOSS)) {
				//TODO: In A1 scenario, you must not remove a Player from the game yet. What to do, then?
				
				// Check if the target has revive ability or not.
				if(!(target.hasCapability(Abilities.REVIVE))) {
					for (Item item : target.getInventory())
						dropActions.add(item.getDropAction(actor));
					for (Action drop : dropActions)
						drop.execute(target, map);
					
					result += System.lineSeparator() + target + " is killed.";

					// If target is enemy, remove it and reward souls
					if(!(target.hasCapability(Abilities.PLAYER))) {
						map.removeActor(target);
						target.asSoul().transferSouls(actor.asSoul()); 		// After defeat enemy, gain souls
					}
					else {
						result += System.lineSeparator() +"                                                                                                                               \n"
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
								+ "                                                                                                                               ";
					}
				}
				else {
					// If target has abilities to revive, give it extra turn to revive.
					if(target.hasCapability(Abilities.REVIVE)){
						target.playTurn(null, getNextAction(), map, null);
						
						// If didn't revive, drop the items, remove it and reward souls.
						if(!target.isConscious()) {
							for (Item item : target.getInventory())
								dropActions.add(item.getDropAction(actor));
							for (Action drop : dropActions)
								drop.execute(target, map);
							map.removeActor(target);
							target.asSoul().transferSouls(actor.asSoul());
							result += System.lineSeparator() + target + " is killed.";

						}
						else
							result += System.lineSeparator() + target + " is revived !";
					}
				}
			}
			// Treat boss separately so that it can display message.
			else {
				result += System.lineSeparator() + target + " is killed.";
				result += System.lineSeparator() +"                                                                                                                                                                                                                                                                                                                                                                                                            \n"
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
	        			+ "";
	        map.locationOf(target).addItem(new PortableItem("Cinders of a Lord", '%'));
	        map.removeActor(target);
	        target.asSoul().transferSouls(actor.asSoul()); 		// After defeat enemy, gain souls
			}
		}
		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
