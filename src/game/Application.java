package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.enemies.LordOfCinder;
import game.enemies.Skeleton;
import game.terrains.Cemetery;
import game.terrains.Dirt;
import game.terrains.Floor;
import game.terrains.Valley;
import game.terrains.Wall;

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
			player.addItemToInventory(new EstusFlask());
			world.addPlayer(player, gameMap.at(38, 12));


			// Place Yhorm the Giant/boss in the map
			gameMap.at(6, 25).addActor(new LordOfCinder("Yhorm the Giant", 'Y', 500));
			
			// FIXED: the Undead will be generated from the Cemetery
			
			// Place several Skeletons in the map
			gameMap.at(0, 0).addActor(new Skeleton());
			gameMap.at(2, 15).addActor(new Skeleton());
			gameMap.at(10, 10).addActor(new Skeleton());
			gameMap.at(20, 20).addActor(new Skeleton());
			gameMap.at(52, 23).addActor(new Skeleton());
			gameMap.at(30, 4).addActor(new Skeleton());
			gameMap.at(33, 6).addActor(new Skeleton());
			gameMap.at(31, 5).addActor(new Skeleton());
			world.run();
	}
}
