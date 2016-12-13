package Model.Player;

import Model.ScoreBoard.YatzyScoreBoard;

/**
 * YatzyPlayer is an extension of Player that has a personal YatzyScoreBoard and a limited
 * amount of dice rolls.
 * @author Isak
 * @author Marianna
 * @author Gustav
 */
public class YatzyPlayer extends Player {
    private final int ROLL_COUNT = 3;

    private int rollsLeft = ROLL_COUNT;
    private YatzyScoreBoard scoreBoard = new YatzyScoreBoard();

    /**
     * Class constructor specifying the name of the player
     * @param name the name of the player
     */
    public YatzyPlayer(String name) {
        super(name);
    }

    /**
     * Gets the players' scoreboard
     * @return the scoreboard
     */
    public YatzyScoreBoard getScoreBoard() {
        return this.scoreBoard;
    }

    /**
     * Method used when the player has performed a roll, decrementing the number of rolls left
     */
    public void roll() {
        if (!this.hasRollsLeft()) {
            return; // throw an error instead of ignoring?
        }

        this.rollsLeft--;
    }

    /**
     * Checks whether player has all rolls left
     * @return boolean indicating if player has all their rolls left
     */
    public boolean hasAllRollsLeft() {
        return this.rollsLeft == ROLL_COUNT;
    }

    /**
     * Checks whether player has any rolls left
     * @return boolean indicating if player has any rolls left
     */
    public boolean hasRollsLeft() {
        return this.rollsLeft > 0;
    }

    /**
     * Resets the amount of rolls the player has left to it's default value
     */
    public void resetRollsLeft() {
        this.rollsLeft = ROLL_COUNT;
    }

    /**
     * Gets a String representation of this class
     * @return a String representation of this class
     */
    @Override
    public String toString() {
        return "YatzyPlayer(name:" + this.name + ", score:" + this.scoreBoard.getTotalScore() + ")";
    }

    /**
	 * Checks whether this and another Object of the same type are equal
	 * @param player the other Object to compare with
	 * @return       a boolean indicating if this and the other Object are equal
	 */
    public boolean equals(YatzyPlayer player) {
        return super.equals(player) && this.scoreBoard.equals(player.scoreBoard);
    }

    /**
	 * Gets an identical new Object of the type class as this
	 * @return a new instance identical to this
	 */
    @Override
    public Player clone() {
        YatzyPlayer copy = (YatzyPlayer) super.clone();

        copy.scoreBoard = this.scoreBoard.clone();

        return copy;
    }
}
