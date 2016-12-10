package Model.ScoreBoard;

public class ScoreBoardCell {
    private Integer value;
    private boolean crossed;

    public ScoreBoardCell() {
        this.reset();
    }

    public boolean isEmpty() {
        return this.value == null;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isCrossed() {
        return this.crossed;
    }

    public void setCrossed(boolean crossed) {
        this.crossed = crossed;
    }

    public void reset() {
        this.value = null;
        this.crossed = false;
    }

    public boolean equals(ScoreBoardCell cell) {
        return this.value.equals(cell.value) && this.crossed == cell.crossed;
    }

    @Override
    public String toString() {
        return "ScoreBoardCell(value" + this.value + ", crossed:" + this.crossed + ")";
    }

    @Override
    public ScoreBoardCell clone() {
        ScoreBoardCell copy = new ScoreBoardCell();

        copy.value = this.value;
        copy.crossed = this.crossed;

        return copy;
    }
}