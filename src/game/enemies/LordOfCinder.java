package game.enemies;

import edu.monash.fit2099.engine.*;
import game.enums.Abilities;

/**
 * The boss of Design o' Souls
 * FIXME: This boss is Boring. It does nothing. You need to implement features here.
 * TODO: Could it be an abstract class? If so, why and how?
 */
public class LordOfCinder extends Enemies {
    /**
     * Constructor.
     */
    public LordOfCinder(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints, 5000 );
        this.addCapability(Abilities.REVIVE); // Only for testing the dead message.
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if(!this.isConscious()) {
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
        }
    	return new DoNothingAction();
    }
}
