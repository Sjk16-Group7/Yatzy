package Model.Dice;

import java.util.ArrayList;
import java.util.HashMap;

public class DiceCollection extends ArrayList<Dice> {
    public DiceCollection() {}

    public DiceCollection(int diceCount) {
        this.add(diceCount);
    }

    public void add(int count, int diceMaxValue) {
        for (int i = 0; i < count; i++) {
            this.add(new Dice(diceMaxValue));
        }
    }

    public void add(int count) {
        for (int i = 0; i < count; i++) {
            this.add(Dice.standard());
        }
    }

    public boolean has(int value) {
        for (Dice dice : this) {
            if (dice.getValue() == value) {
                return true;
            }
        }

        return false;
    }

    public int getSum() {
        int sum = 0;

        for (Dice dice : this) {
            sum += dice.getValue();
        }

        return sum;
    }

    public void roll() {
        for (Dice dice : this) {
            dice.roll();
        }
    }

    public void rollSome(boolean[] diceToRoll) {
        for (int i = 0; i < diceToRoll.length; i++) {
            boolean shouldRoll = diceToRoll[i];

            if (shouldRoll) {
                this.get(i).roll();
            }
        }
    }

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

    @Override
    public String toString() {
        // TODO
        return "";
    }

    @Override
    public DiceCollection clone() {
        return (DiceCollection) super.clone();
    }
}
