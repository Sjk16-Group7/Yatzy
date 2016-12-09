package Model;

public class Dice {
	
	private int surfaceValue;
	
	public void roll() {
        this.surfaceValue = (int)(Math.random() * 6 + 1);
    }
	
	 public int getValue() {
		return surfaceValue;
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
