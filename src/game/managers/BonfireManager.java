package game.managers;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
/**
 * Class representing bonfire manager
 *
 */
public class BonfireManager {
	/**
	 * Attribute the map of bonfire
	 */
	private List<GameMap> bonfireMap;
	/**
	 * Attribute the location of bonfire
	 */
	private List<Location> bonfireLocation;
	/**
     * A singleton Bonfire manager instance
     */
	private static BonfireManager instance;
	/**
	 * Static method to crate bonfire manager instance
	 * @return	bonfire manager instance
	 */
	public static BonfireManager getInstance(){
        if(instance == null){
            instance = new BonfireManager();
        }
        return instance;
    }
	/**
	 * Constructor of bonfire manager
	 */
	private BonfireManager(){
		bonfireMap = new ArrayList<>();
		bonfireLocation = new ArrayList<>();
    }
	/**
	 * Method to add the latest bonfire that interact with
	 * @param map	the map of latest bonfire at
	 * @param location	the location of bonfire
	 */
	public void appendBonfireInstance(GameMap map, Location location){
		bonfireMap.add(map);
		bonfireLocation.add(location);
    }
	/**
	 * Method to get the map of latest bonfire
	 * @return	GameMap
	 */
	public GameMap getMap(){
		return bonfireMap.get(bonfireMap.size() - 1);
	}
	/**
	 * Method to get the location of latest bonfire
	 * @return	Location
	 */
	public Location getLocation(){
		return bonfireLocation.get(bonfireLocation.size() - 1);
	}
	/**
	 * Method to indicate the list is empty
	 * @return  true if is empty, otherwise return false
	 */
	public boolean isEmpty() {
		return bonfireMap.size() == 0;
	}
}
