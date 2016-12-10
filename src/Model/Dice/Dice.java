package Model.Dice;

import Model.Dice;

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
		return "Dice value is: " + getValue();
	}

	public Dice clone() {
		Dice copyDice = new Dice();
		copyDice.surfaceValue = this.surfaceValue;

		return copyDice;
	}

	public boolean equals(Dice dice) {
		return this.surfaceValue == dice.surfaceValue;
	}
}
