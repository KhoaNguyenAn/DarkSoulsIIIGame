package game.managers;
/**
 * Class representing the souls manager.
 * @author Dongzheng Wu
 */
public class SoulsManager {
	/**
	 * Attributes store the number of souls
	 */
	private int souls;
	/**
	 * Constructor
	 * @param souls		the number of souls the owner have initially
	 */
	public SoulsManager(int souls) {
		this.souls = souls;
	}
	/**
	 * Constructor with zero souls
	 */
	public SoulsManager() {
		this.souls = 0;
	}
	/**
	 * Method to add the souls
	 * @param souls		number of souls added
	 */
	public void add(int souls) {
		this.souls += souls;
	}
	/**
	 * Method to subtract the souls
	 * @param souls		number of souls reduced
	 * @return		true if transaction successful, otherwise return false
	 */
	public boolean sub(int souls) {
		if (this.souls < souls)
			return false;
		else
			this.souls -= souls;
		return true;
	}
	/**
	 * Clear the number of souls
	 */
	public void clear() {
		this.souls = 0;
	}
	/**
	 * Get the number of souls
	 * @return		number of souls
	 */
	public int getSouls() {
		return souls;
	}
}
