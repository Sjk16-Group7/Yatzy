package Model;

public class ScoreBoardCell {
    private String value;
    private boolean crossed;

    public ScoreBoardCell() {
        this.reset();
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCrossed() {
        return this.crossed;
    }

    public void setCrossed(boolean crossed) {
        this.crossed = crossed;
    }

    public void reset() {
        this.value = "";
    }
}