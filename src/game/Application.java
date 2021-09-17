package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.enemies.Enemies;
import game.enemies.LordOfCinder;
import game.enemies.Skeleton;
import game.enemies.Undead;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Cemetery());

			List<String> map = Arrays.asList(
					"..++++++..+++...........................++++......+++.................+++.......",
					"........+++++..............................+++++++.................+++++........",
					"...........+++.......................................................+++++......",
					"........................................................................++......",
					"................................................................c........+++....",
					"............................+.............................................+++...",
					".............................+++.......++++.....................................",
					"...............c.............++.......+......................++++...............",
					".............................................................+++++++............",
					"..................................###___###...................+++...............",
					"..................................#_______#......................+++............",
					"....c......++.....................#_______#.......................+.............",
					".........+++......................#_______#........................++...........",
					"............+++...................####_####..........................+..........",
					"..............+......................................................++.........",
					"..............++.................................................++++++.........",
					"............+++...................................................++++..........",
					"+..................................................................++...........",
					"++...+++..................c......................................++++...........",
					"+++......................................+++........................+.++........",
					"++++.......++++.........................++.........................+....++......",
					"#####___#####++++......................+...............c...............+..+.....",
					"_..._....._._#.++......................+...................................+....",
					"...+.__..+...#+++...........................................................+...",
					"...+.....+._.#.+.....+++++...++..............................................++.",
					"___.......___#.++++++++++++++.+++.............................................++");
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Actor player = new Player("Unkindled (Player)", '@', 100);
			world.addPlayer(player, gameMap.at(38, 12));

			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new LordOfCinder("Yhorm the Giant", 'Y', 500));

			// Place a Hollow in the the map
			// FIXME: the Undead should be generated from the Cemetery
			// FIXED: the Undead will be generated from the Cemetery
//			gameMap.at(32, 7).addActor(new Undead("Undead"));
			gameMap.at(32, 7).addActor(new Skeleton());
			gameMap.at(2, 15).addActor(new Skeleton());
			gameMap.at(10, 10).addActor(new Skeleton());
			gameMap.at(20, 20).addActor(new Skeleton());
			gameMap.at(52, 23).addActor(new Skeleton());
			world.run();

	}
}
