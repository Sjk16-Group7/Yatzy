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
     * @param diceCount the initial number of Dice
     */
    public DiceCollection(int diceCount) {
        this.addAmount(diceCount);
    }

    /**
     * Adds a specific amount of custom-sided Dice
     * @param amount       amount of Dice to add
     * @param diceMaxValue amount of sides of each Dice
     */
    public void addValue(int amount, int diceMaxValue) {
        for (int i = 0; i < amount; i++) {
            this.add(new Dice(diceMaxValue));
        }
    }

    /**
     * Adds a specific amount of standard Dice
     * @param amount amount of Dice to add
     */
    public void addAmount(int amount) {
        for (int i = 0; i < amount; i++) {
            this.add(Dice.standard());
        }
    }

    /**
     * Removes a single dice with a specific value
     * @param value the value to look for
     */
    public void removeValue(int value) {
        for (Dice dice : this) {
            if (dice.getValue() == value) {
                this.remove(dice);
                return;
            }
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
     * Checks whether this contains dice with specific values
     * @param values values to look for
     * @return boolean indicating whether this contains dice with specific values
     */
    public boolean hasAll(int[] values) {
        for (int value : values) {
            if (!this.has(value)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether this contains dice with any specified values
     * @param values values to look for
     * @return boolean indicating whether this contains dice with any specified values
     */
    public boolean hasAny(int[] values) {
        for (int value : values) {
            if (this.has(value)) {
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
     * Gets an identical new Object of the type class as this. All elements are also cloned.
     * @return a new instance identical to this
     */
    @Override
    public DiceCollection clone() {
        DiceCollection collection = new DiceCollection();

        for (Dice dice : this) {
            collection.add(dice.clone());
        }

        return collection;
    }
}
