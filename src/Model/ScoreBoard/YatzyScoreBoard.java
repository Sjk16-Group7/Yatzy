package Model.ScoreBoard;

import java.util.Arrays;

import Model.Dice.DiceCombination;

/**
 * YatzyScoreBoard is an extension of a ScoreBoard, with additional methods related to the game
 * Yahtzee. It's a personal scoreboard and therefore only has one column.
 * @author Isak
 * @author Gustav
 */
public class YatzyScoreBoard extends ScoreBoard {
    private static final int TOTAL_ROWS = DiceCombination.values().length;
    private static final int UPPER_SECTION_ROWS = 6;
    private static final int LOWER_SECTION_ROWS = TOTAL_ROWS - UPPER_SECTION_ROWS;

    /**
     * Class default constructor
     */
    public YatzyScoreBoard() {
        super(TOTAL_ROWS, 1);
    }

    /**
     * Gets the bonus score that this scoreboard yields
     * @return the bonus score
     */
    public int getBonus() {
        int bonus = 0;

        if (this.getUpperSection().getSum() >= 63) {
            bonus = 50;
        }

        return bonus;
    }

    /**
     * Gets the upper section of this scoreboard
     * @return the upper section
     */
    public ScoreBoard getUpperSection() {
        ScoreBoardCell[][] rows = Arrays.copyOfRange(
            this.board,
            0,
            UPPER_SECTION_ROWS
        );

        return ScoreBoard.fromRows(rows);
    }

    /**
     * Gets the lower section of this scoreboard
     * @return the lower section
     */
    public ScoreBoard getLowerSection() {
        ScoreBoardCell[][] rows = Arrays.copyOfRange(
            this.board,
            UPPER_SECTION_ROWS,
            UPPER_SECTION_ROWS + LOWER_SECTION_ROWS
        );

        return ScoreBoard.fromRows(rows);
    }

    /**
     * Gets the total score, including bonus, that this scoreboard yields
     * @return the total score
     */
    public int getTotalScore() {
        return this.getSum() + this.getBonus();
    }

    /**
     * Gets a cell associated with a specific DiceCombination
     * @param combination the combination
     * @return             the cell
     */
    public ScoreBoardCell getCell(DiceCombination combination) {
        int index = Arrays.asList(DiceCombination.values()).indexOf(combination);

        return this.getCell(index, 0);
    }

    /**
	 * Gets an identical new Object of the type class as this
	 * @return a new instance identical to this
	 */
    @Override
    public YatzyScoreBoard clone() {
        return (YatzyScoreBoard) super.clone();
    }

    /**
	 * Gets a String representation of this class
	 * @return a String representation of this class
	 */
    @Override
    public String toString() {
        return "YatzyScoreBoard(rows:" + this.rows + ", columns:" + this.columns + ")";
    }
}
