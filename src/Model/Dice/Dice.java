package Model.Dice;

public class Dice {
    private int maxValue;
	private int surfaceValue;

	public Dice(int maxValue) {
	    this.maxValue = maxValue;
	    this.roll();
    }

    public static Dice standard() {
	    return new Dice(6);
    }

	public void roll() {
        this.surfaceValue = (int)(Math.random() * maxValue + 1);
    }

	 public int getValue() {
		return this.surfaceValue;
	}

	public void setValue(int value) {
	    this.surfaceValue = value;
    }

    public String toString() {
        // TODO
        return "";
    }

    //public Dice clone() {
        // TODO
    //}
}
