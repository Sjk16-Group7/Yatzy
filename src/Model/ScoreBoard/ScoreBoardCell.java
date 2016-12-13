package Model.ScoreBoard;

/**
 * ScoreBoardCell is a class representing a single cell of a ScoreBoard. It may be empty, crossed
 * out or contain a number.
 * @author Marianna
 */
public class ScoreBoardCell {
    private Integer value;
    private boolean crossed;

    /**
     * Class default constructor, sets the cell to its' default value (empty and not crossed over)
     */
    public ScoreBoardCell() {
        this.reset();
    }

    /**
     * Checks whether this is empty
     * @return a boolean indicating whether this is empty
     */
    public boolean isEmpty() {
        return this.value == null;
    }

    /**
     * Gets the value of the cell. Will return 0 if empty
     * @return the value
     */
    public int getValue() {
        if (this.value == null) {
            return 0;
        }

        return this.value;
    }

    /**
     * Sets the value of the cell
     * @param value the value. May be null to set it as empty.
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Checks whether this is crossed out
     * @return a boolean indicating whether this is crossed out
     */
    public boolean isCrossed() {
        return this.crossed;
    }

    /**
     * Sets the crossed state
     * @param crossed boolean indicating whether to set it as crossed
     */
    public void setCrossed(boolean crossed) {
        this.crossed = crossed;
    }

    /**
     * Resets this to its' default values
     */
    public void reset() {
        this.value = null;
        this.crossed = false;
    }

    /**
	 * Checks whether this and another Object of the same type are equal
	 * @param cell the other Object to compare with
	 * @return     a boolean indicating if this and the other Object are equal
	 */
    public boolean equals(ScoreBoardCell cell) {
        return this.value.equals(cell.value) && this.crossed == cell.crossed;
    }

    /**
     * Gets a String representation of this class
     * @return a String representation of this class
     */
    @Override
    public String toString() {
        return "ScoreBoardCell(value" + this.value + ", crossed:" + this.crossed + ")";
    }

    /**
	 * Gets an identical new Object of the type class as this
	 * @return a new instance identical to this
	 */
    @Override
    public ScoreBoardCell clone() {
        ScoreBoardCell copy = new ScoreBoardCell();

        copy.value = this.value;
        copy.crossed = this.crossed;

        return copy;
    }
}