package src.Model;

public class Dice {
	private int surfaceValue;
	
	public void roll() {
        this.surfaceValue = (int)(Math.random() * 6 + 1);
    }
	
	 public int getValue() {
		return surfaceValue;
	}
}
