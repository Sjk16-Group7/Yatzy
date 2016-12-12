package Model.Player;

/**
 * Player is a class representing a general player of some game.
 * @author Marianna
 */
public class Player {
	protected String name;

	/**
	 * Class constructor specifying the name of the player
	 * @param name the name
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of this player
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets a String representation of this class
	 * @return a String representation of this class
	 */
	@Override
	public String toString() {
		return "Player(name:" + this.name + ")";
	}

	/**
	 * Checks whether this and another Object of the same type are equal
	 * @param player the other Object to compare with
	 * @return       a boolean indicating if this and the other Object are equal
	 */
	public boolean equals(Player player) {
        return this.name.equals(player.name); // are players with the same name equal, though?
    }

	/**
	 * Gets an identical new Object of the type class as this
	 * @return a new instance identical to this
	 */
	@Override
	public Player clone() {
		return new Player(this.name);
	}
}
