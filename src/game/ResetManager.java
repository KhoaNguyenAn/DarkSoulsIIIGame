package game;

import game.interfaces.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 * TODO: you may modify (add or remove) methods in this class if you think they are not necessary.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 * A3: Think about how will you improve this implementation in the future assessment.
 * What could be the drawbacks of this implementation?
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     * FIXED: not it will reset all the resettable instance and then clean them up if they are
     * not exist anymore.
     */
    public void run(){
    	for(Resettable obj : resettableList) {
    		obj.resetInstance();
    	}
    	cleanUp();
    }

    /**
     * Add the Resettable instance to the list
     * FIXED: it will append all the resettable instance in the reset list
     * @param resettable the interface instance
     */
    public void appendResetInstance(Resettable resettable){
    	resettableList.add(resettable);
    }

    /**
     * clean up instances (actor, item, or ground) that doesn't exist anymore in the map
     * FIXED: it will clean all not exist instances.
     */
    private void cleanUp(){
    	for(int i = 0; i < resettableList.size(); i++) {
    		if(!resettableList.get(i).isExist()) {
    			resettableList.remove(i);
    		}
    	}
    }
}
