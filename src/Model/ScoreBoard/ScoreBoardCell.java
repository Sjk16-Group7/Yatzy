package Model.ScoreBoard;

public class ScoreBoardCell {
    private Integer value;
    private boolean crossed;
    
    public ScoreBoardCell() { 
        this.reset();
    }
    
    public void reset() { 
        this.value = null;
    }
   
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }

    public boolean isCrossed() {
        return this.crossed;
    }

    public void setCrossed(boolean crossed) {
        this.crossed = crossed;
    }

	public boolean isEmpty() {
		return this.value == null;
	}

	public boolean equals(ScoreBoardCell cell) {
		// return this.value == cell.value && this.crossed == cell.crossed;
		if (this.value != cell.value)
			return false;
		else if (this.crossed != cell.crossed)
			return false;
		else
			return true;
	}

	public String toString() {
		return "ScoreBoardCell()";
	}

	public ScoreBoardCell clone() {
		ScoreBoardCell copy = new ScoreBoardCell();
		// copy.setValue(this.value);
		copy.value = this.value;
		copy.crossed = this.crossed;

		return copy;
	}
}

