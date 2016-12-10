package Model.Player;

import Model.ScoreBoard.YatzyScoreBoard;

public class YatzyPlayer extends Player {
    private final int ROLL_COUNT = 3;

    private int rollsLeft = ROLL_COUNT;
    private YatzyScoreBoard scoreBoard = new YatzyScoreBoard(1);

    public YatzyPlayer(String name) {
        super(name);
    }

    public YatzyScoreBoard getScoreBoard() {
        return this.scoreBoard;
    }

    public void roll() {
        this.rollsLeft--;
    }

    public boolean hasRollsLeft() {
        return this.rollsLeft > 0;
    }

    public void resetRollsLeft() {
        this.rollsLeft = ROLL_COUNT;
    }

    @Override
    public String toString() {
        return "YatzyPlayer(name:" + this.name + ", score:" + this.scoreBoard.getTotalScore() + ")";
    }

    public boolean equals(YatzyPlayer player) {
        return super.equals(player) && this.scoreBoard.equals(player.scoreBoard);
    }

    @Override
    public Player clone() {
        YatzyPlayer copy = (YatzyPlayer) super.clone();

        copy.scoreBoard = this.scoreBoard.clone();

        return copy;
    }
}
