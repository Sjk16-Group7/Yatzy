package Model.Dice;

/**
 * Dice is a class representing a single real life die that may have an arbitrary number of sides,
 * normally the standard of six.
 * @author Marianna
 */
public class Dice {
    private int maxValue;
	private int value;

	/**
	 * Class constructor specifying the maximum value the dice may take
	 * @param maxValue the maximum value the dice may take
	 */
	public Dice(int maxValue) {
	    this.maxValue = maxValue;

	    // assign a random value on creation
	    this.roll();
    }

	/**
	 * Static constructor
	 * @return a standard Dice with 6 sides
	 */
	public static Dice standard() {
	    return new Dice(6);
    }

	/**
	 * Roll the dice, giving a new random value
	 */
	public void roll() {
        this.value = (int)(Math.random() * maxValue + 1);
    }

	/**
	 * Gets the value of the current up-facing side of this Dice
	 * @return the value of the up-facing side
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Sets the value of the current up-facing side of this Dice
	 * @param value the new value of the up-facing side
	 */
	public void setValue(int value) {
	    this.value = value;
    }

	/**
	 * Gets a String representation of this class
	 * @return a String representation of this class
	 */
	@Override
	public String toString() {
		return "Dice(value:" + this.value + ")";
	}

	/**
	 * Gets an identical new Object of the type class as this
	 * @return a new instance identical to this
	 */
	@Override
	public Dice clone() {
		Dice copy = new Dice(this.maxValue);
		copy.value = this.value;

		return copy;
	}

	/**
	 * Checks whether this and another Object of the same type are equal
	 * @param dice the other Object to compare with
	 * @return      a boolean indicating if this and the other Object are equal
	 */
	public boolean equals(Dice dice) {
		return this.value == dice.value;
	}
}
