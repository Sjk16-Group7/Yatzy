package Model.Dice;

public class Dice {
    private int maxValue;
	private int value;

	public Dice(int maxValue) {
	    this.maxValue = maxValue;
	    this.roll();
    }

    public static Dice standard() {
	    return new Dice(6);
    }

	public void roll() {
        this.value = (int)(Math.random() * maxValue + 1);
    }

	 public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
	    this.value = value;
    }

	@Override
	public String toString() {
		return "Dice(value:" + this.value + ")";
	}

	@Override
	public Dice clone() {
		Dice copy = new Dice(this.maxValue);
		copy.value = this.value;

		return copy;
	}

	public boolean equals(Dice dice) {
		return this.value == dice.value;
	}
}
