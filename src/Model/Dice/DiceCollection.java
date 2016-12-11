package Model.Dice;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * DiceCollection is an ArrayList containing Objects of the class Dice, with additional methods
 * to work with the Objects.
 * @author Isak
 */
public class DiceCollection extends ArrayList<Dice> {
    /**
     * Class default constructor, creates an empty DiceCollection.
     */
    public DiceCollection() {}

    /**
     * Class constructor, specifying number of Dice to create and add on creation.
     * @param diceCount
     */
    public DiceCollection(int diceCount) {
        this.add(diceCount);
    }

    /**
     * Adds a specific amount of custom-sided Dice
     * @param count         amount of Dice to add
     * @param diceMaxValue amount of sides of each Dice
     */
    public void add(int count, int diceMaxValue) {
        for (int i = 0; i < count; i++) {
            this.add(new Dice(diceMaxValue));
        }
    }

    /**
     * Adds a specific amount of standard Dice
     * @param count amount of Dice to add
     */
    public void add(int count) {
        for (int i = 0; i < count; i++) {
            this.add(Dice.standard());
        }
    }

    /**
     * Checks whether this contains a Dice with a specific value
     * @param  value value to look for
     * @return        boolean indicating whether this contains a Dice with a specific value
     */
    public boolean has(int value) {
        for (Dice dice : this) {
            if (dice.getValue() == value) {
                return true;
            }
        }

        return false;
    }

    /**
     * Sums all Dice values
     * @return the sum of all Dice values
     */
    public int getSum() {
        int sum = 0;

        for (Dice dice : this) {
            sum += dice.getValue();
        }

        return sum;
    }

    /**
     * Rolls all Dice
     */
    public void roll() {
        for (Dice dice : this) {
            dice.roll();
        }
    }

    /**
     * Rolls some dice
     * @param diceToRoll array containing the indices of the Dice to roll
     */
    public void rollSome(int[] diceToRoll) {
        for (int index : diceToRoll) {
            this.get(index).roll();
        }
    }

    /**
     * Gets a HashMap indicating what Dice values are present in this Collection, and how many
     * times each of them occur.
     * @return a HashMap
     */
    public HashMap<Integer, Integer> occurrences() {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (Dice dice : this) {
            int value = dice.getValue();

            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }

        return map;
    }

    /**
     * Gets an int specifying how many time a specific value occurs in this Collection
     * @param value the value to look for
     * @return       an int specifying how many times a specific value occurs
     */
    public int occurrences(int value) {
        int count = 0;

        for (Dice dice : this) {
            int diceValue = dice.getValue();

            if (diceValue == value) {
                count++;
            }
        }

        return count;
    }

    /**
     * Gets a String representation of this class
     * @return a String representation of this class
     */
    @Override
    public String toString() {
        return "DiceCollection(size:" + this.size() + ")";
    }

    /**
     * Gets an identical new Object of the type class as this
     * @return a new instance identical to this
     */
    @Override
    public DiceCollection clone() {
        return (DiceCollection) super.clone();
    }
}
