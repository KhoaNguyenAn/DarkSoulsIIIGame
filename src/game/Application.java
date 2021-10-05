package game;


import edu.monash.fit2099.engine.*;
import game.Weapon.BroadSword;
import game.Weapon.StormRuler;
import game.Weapon.YhormsGiantMachete;
import game.enemies.LordOfCinder;
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

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Valley(), new Cemetery(), new Bonfire(), new Chest());

			List<String> map = Arrays.asList(
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
			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Location bonfire = gameMap.at(38,11);

			Actor player = new Player("Unkindled (Player)", '@', 1000000, bonfire);	//TODO: Need to fixed after test
			world.addPlayer(player, gameMap.at(38, 12));

			// Place Yhorm the Giant/boss in the map
			LordOfCinder yhorm = new LordOfCinder("Yhorm the Giant", 'Y', 500);
			LordOfCinder aldrich = new LordOfCinder("Aldrich the Devourer", 'A', 350);
			
			yhorm.addItemToInventory(new YhormsGiantMachete(yhorm));
			aldrich.addItemToInventory(new BroadSword());	//TODO: Need to be replaced with DarkmoonLongbow
			
			gameMap.at(6, 25).addActor(yhorm);	
			gameMap.at(38, 9).addActor(aldrich);	//TODO: Need to placed on the second map with certain terrains around
			gameMap.at(7, 25).addItem(new StormRuler());
			
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
			gameMap.at(37, 11).addActor(new Vendor());
			world.run();
	}
}
