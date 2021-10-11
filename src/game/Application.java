package game;

import edu.monash.fit2099.engine.*;
import game.Weapon.StormRuler;
import game.enemies.Skeleton;
import game.terrains.*;

import java.util.Arrays;
import java.util.List;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(),
				new Cemetery(), new Bonfire(), new Chest());

		List<String> ProfaneCapital = Arrays.asList(
				"..++++++..+++...........................++++......+++.................+++.......",
				"........+++++..............................+++++++.................+++++........",
				"...........+++.......................................................+++++......",
				"....................?...................................................++......",
				"...............................................?................c........+++....",
				"............................+.............................................+++...",
				".............................+++.......++++.....................................",
				"...............c.............++.......+......................++++...............",
				".............................................................+++++++............",
				"....................?.............###___###...................+++...............",
				"..................................#_______#......................+++............",
				"....c......++.....................#___B___#.............?.........+.............",
				".........+++......................#_______#........................++...........",
				"............+++...................####_####..........................+..........",
				"..............+.............................?........................++.........",
				"..............++.................................................++++++.........",
				"............+++.....?.............................................++++..........",
				"+..................................................................++...........",
				"++...+++..................c.................?....................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.........................+....++......",
				"#####___#####++++......................+...............c...............+..+.....",
				"_..._....._._#.++......................+...................................+....",
				"...+.__..+...#+++..................................?........................+...",
				"...+.....+._.#.+.....+++++...++..............................................++.",
				"___.......___#.++++++++++++++.+++.............................................++");

		List<String> AnorLondo = Arrays.asList(
				"..++++++..+++...........................++++......+++.................+++.......",
				"........+++++..............................+++++++.................+++++........",
				"...........+++.......................................................+++++......",
				"..............++.................................................++++++.........",
				"............+++.....?.............................................++++..........",
				"++...+++..................c.................?....................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.........................+....++......",
				"#####___#####++++......................+...............c...............+..+.....",
				"_..._....._._#.++......................+...................................+....",
				"...+.__..+...#+++..................................?........................+...",
				"...+.....+._.#.+.....+++++...++..............................................++.",
				"___.......___#.++++++++++++++.+++.............................................++");
		GameMap firstGameMap = new GameMap(groundFactory, ProfaneCapital);
		GameMap secondGameMap = new GameMap(groundFactory, AnorLondo);

		// two object for Bonfire, pass the name
		// Location firstBonfire =  firstGameMap.at(38, 11);
		// Location secondBonfire = secondGameMap.at(38, 0);
		//Bonfire bonfire = new Bonfire("Anor Londo’s Bonfire");
		
		world.addGameMap(firstGameMap);
		world.addGameMap(secondGameMap);
		// place this fog door at the most southern part of the first game map at any horizontal axis
		Location fogDoor = firstGameMap.at(38,25);
		firstGameMap.at(0, 11).setGround(new FogDoor(fogDoor,"AnorLondo",secondGameMap));
		// firstGameMap.at(0,0).setGround(new firstBonfire());
		// secondGameMap.at(0,0).setGround(new secondBonfire());
		


		Location bonfire = firstGameMap.at(38,11);
		// Bonfire Manager class
		// activate Bonfire by Bonfire Manager
		// render Bonfire -> set Ground method
		Actor player = new Player("Unkindled (Player)", '@', 100000, bonfire); // TODO: Need to fixed after test
		world.addPlayer(player, firstGameMap.at(38, 12));

		// Place bosses/weapon in the map

		// firstGameMap.at(6, 25).addActor(new YhormTheGiant());
		// firstGameMap.at(38, 9).addActor(new AldrichTheDevourer()); // TODO: Need to placed on the second map with certain
																// terrains around
		firstGameMap.at(7, 25).addItem(new StormRuler());
		// FIXED: the Undead will be generated from the Cemetery

		// Place several Skeletons in the map
		firstGameMap.at(0, 0).addActor(new Skeleton());
		firstGameMap.at(2, 15).addActor(new Skeleton());
		firstGameMap.at(10, 10).addActor(new Skeleton());
		firstGameMap.at(20, 20).addActor(new Skeleton());
		firstGameMap.at(52, 23).addActor(new Skeleton());
		firstGameMap.at(30, 4).addActor(new Skeleton());
		firstGameMap.at(33, 6).addActor(new Skeleton());
		firstGameMap.at(31, 5).addActor(new Skeleton());
		firstGameMap.at(37, 11).addActor(new Vendor());


		// second map
		// Place a new Bonfire somewhere on the second map Anor Londo’s Bonfire
		//secondGameMap.at(x, y).add(38,0);

		world.run();
	}
}
