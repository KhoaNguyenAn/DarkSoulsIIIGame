package game;

import edu.monash.fit2099.engine.*;
import game.Weapon.StormRuler;
import game.enemies.AldrichTheDevourer;
import game.enemies.Skeleton;
import game.enemies.YhormTheGiant;
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
				"..++++++..+++..............#..................#.......................+++.......",
				"........+++++..............#__________________#....................+++++........",
				"........c..+++.......................................................+++++......",
				"......c.......++.................................................++++++.........",
				"............+++.....?.............................................++++..........",
				"++...+++..................c.................?....................++++...........",
				"+++......................................+++........................+.++........",
				"++++.......++++.........................++.........................+....++......",
				".............##________________##....................+...............c..........",
				"....c........#_..._....._._.....#.......c............+..........................",
				".....c.......#...+.__..+........#................................?..............",
				".............#...+.....+._......#..........++...................................",
				".............#___........#_.....#...........+++....................c............");
		GameMap firstGameMap = new GameMap(groundFactory, ProfaneCapital);
		GameMap secondGameMap = new GameMap(groundFactory, AnorLondo);

		// two object for Bonfire, pass the name
		Location firstBonfireLocation = firstGameMap.at(38,11);
		Bonfire firstBonfire = new Bonfire("Firelink Shrine's Bonfire",true,firstGameMap,secondGameMap);
		Bonfire secondBonfire = new Bonfire("Profane Capital's Bonfire",false,firstGameMap,secondGameMap);
		firstGameMap.at(38,11).setGround(firstBonfire);
		secondGameMap.at(40,0).setGround(secondBonfire);
		
		world.addGameMap(firstGameMap);
		world.addGameMap(secondGameMap);
		// place this fog door at the most southern part of the first game map at any horizontal axis
		Location fogDoorAtFirstMap = firstGameMap.at(38,25);
		Location fogDoorAtSecondMap = secondGameMap.at(38,0);
		firstGameMap.at(fogDoorAtFirstMap.x(),fogDoorAtFirstMap.y()).setGround(new FogDoor(fogDoorAtSecondMap,"AnorLondo",secondGameMap));
		

		// Bonfire Manager class
		// activate Bonfire by Bonfire Manager
		// render Bonfire -> set Ground method
		Actor player = new Player("Unkindled (Player)", '@', 100000, firstBonfireLocation); // TODO: Need to be fixed after test
		world.addPlayer(player, firstGameMap.at(38, 12));

		// Place bosses/weapon in the map

		firstGameMap.at(6, 25).addActor(new YhormTheGiant());
		secondGameMap.at(20, 12).addActor(new AldrichTheDevourer());
		 
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
		secondGameMap.at(0, 0).addActor(new Skeleton());
		secondGameMap.at(2, 9).addActor(new Skeleton());
		secondGameMap.at(10, 10).addActor(new Skeleton());
		secondGameMap.at(20, 5).addActor(new Skeleton());
		secondGameMap.at(52, 8).addActor(new Skeleton());
		secondGameMap.at(30, 2).addActor(new Skeleton());
		secondGameMap.at(33, 7).addActor(new Skeleton());
		secondGameMap.at(31, 5).addActor(new Skeleton());

		// add more skeleton
		secondGameMap.at(30, 4).addActor(new Skeleton());
		secondGameMap.at(33, 6).addActor(new Skeleton());
		
		world.run();
	}
}
