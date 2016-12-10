package Model.ScoreBoard;

import java.util.Arrays;

import Model.Dice.DiceCombination;

public class YatzyScoreBoard extends ScoreBoard {
    private static final int TOTAL_ROWS = DiceCombination.values().length;
    private static final int UPPER_SECTION_ROWS = 6;
    private static final int LOWER_SECTION_ROWS = TOTAL_ROWS - UPPER_SECTION_ROWS;

    public YatzyScoreBoard(int players) {
        super(TOTAL_ROWS, players);
    }

    public int getBonus() {
        int bonus = 0;

        if (this.getUpperSection().getSum() >= 63) {
            bonus = 50;
        }

        return bonus;
    }

    public ScoreBoard getUpperSection() {
        ScoreBoardCell[][] rows = Arrays.copyOfRange(
            this.board,
            0,
            UPPER_SECTION_ROWS
        );

        return ScoreBoard.fromRows(rows);
    }

    public ScoreBoard getLowerSection() {
        ScoreBoardCell[][] rows = Arrays.copyOfRange(
            this.board,
            UPPER_SECTION_ROWS,
            UPPER_SECTION_ROWS + LOWER_SECTION_ROWS
        );

        return ScoreBoard.fromRows(rows);
    }

    public int getTotalScore() {
        return this.getSum() + this.getBonus();
    }

    @Override
    public YatzyScoreBoard clone() {
        return (YatzyScoreBoard) super.clone();
    }

    @Override
    public String toString() {
        return "YatzyScoreBoard(rows:" + this.rows + ", columns:" + this.columns + ")";
    }
}
