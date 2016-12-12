package Model.Dice;

import java.util.HashMap;

/**
 * YatzyDiceCollection is an extension of DiceCollection that always contains 5 Dice and has
 * additional methods specific to the game Yahtzee.
 * @author Isak
 * @author Marianna
 * @author Gustav
 */
public class YatzyDiceCollection extends DiceCollection {
    /**
     * Class default constructor
     */
    public YatzyDiceCollection() {
        super(5);
    }

    /**
     * Checks whether a specific combination is valid with this Collection of Dice
     * @param combination the combination
     * @return              boolean indicating if combination is valid with this
     */
    public boolean match(DiceCombination combination) {
        // combination is valid if the score it yields isn't 0
        return this.getScore(combination) > 0;
    }

    /**
     * Gets the score this collection yields using a specific combination
     * @param combination the combination
     * @return             the score
     */
    public int getScore(DiceCombination combination) {
        switch (combination) {
            case ACES:            return this.getAcesScore();
            case TWOS:            return this.getTwosScore();
            case THREES:          return this.getThreesScore();
            case FOURS:           return this.getFoursScore();
            case FIVES:           return this.getFivesScore();
            case SIXES:           return this.getSixesScore();
            case ONE_PAIR:        return this.getOnePairScore();
            case TWO_PAIRS:       return this.getTwoPairsScore();
            case THREE_OF_A_KIND: return this.getThreeOfAKindScore();
            case FOUR_OF_A_KIND:  return this.getFourOfAKindScore();
            case SMALL_STRAIGHT:  return this.getSmallStraightScore();
            case LARGE_STRAIGHT:  return this.getLargeStraightScore();
            case FULL_HOUSE:      return this.getFullHouseScore();
            case CHANCE:          return this.getChanceScore();
            case YATZY:           return this.getYatzyScore();
        }

        // yield a score of zero if combination isn't supported.
        // this should probably throw an Exception.
        return 0;
    }

    /**
     * Gets score of Aces that this Collection yields
     * @return the score
     */
    private int getAcesScore() {
        return this.occurrences(1);
    }

    /**
     * Gets score of Twos that this Collection yields
     * @return the score
     */
    private int getTwosScore() {
        return this.occurrences(2) * 2;
    }

    /**
     * Gets score of Threes that this Collection yields
     * @return the score
     */
    private int getThreesScore() {
        return this.occurrences(3) * 3;
    }

    /**
     * Gets score of Fours that this Collection yields
     * @return the score
     */
    private int getFoursScore() {
        return this.occurrences(4) * 4;
    }

    /**
     * Gets score of Fives that this Collection yields
     * @return the score
     */
    private int getFivesScore() {
        return this.occurrences(5) * 5;
    }

    /**
     * Gets score of Sixes that this Collection yields
     * @return the score
     */
    private int getSixesScore() {
        return this.occurrences(6) * 6;
    }

    /**
     * Gets score of One Pair that this Collection yields
     * @return the score
     */
    private int getOnePairScore() {
        int score = 0;
        HashMap<Integer, Integer> occurences = this.occurrences();

        for (int value : occurences.keySet()) {
            if (occurences.get(value) < 2) {
                // skip if there are less than 2 of value because it's not a pair
                continue;
            }

            if (score < value * 2) {
                score = value * 2;
            }
        }

        return score;
    }

    /**
     * Gets score of Two Pairs that this Collection yields
     * @return the score
     */
    private int getTwoPairsScore() {
        int firstPairScore = 0;
        int secondPairScore = 0;
        HashMap<Integer, Integer> occurences = this.occurrences();

        for (int value : occurences.keySet()) {
            if (occurences.get(value) < 2) {
                // skip if there are less than 2 of value because it's not a pair
                continue;
            }

            if (firstPairScore < value * 2 && firstPairScore <= secondPairScore) {
                firstPairScore = value * 2;
            } else if (secondPairScore < value * 2) {
                secondPairScore= value * 2;
            }
        }

        if (firstPairScore == 0 || secondPairScore == 0) {
            // there are not two pairs
            return 0;
        }

        return firstPairScore + secondPairScore;
    }

    /**
     * Gets score of Three of a Kind that this Collection yields
     * @return the score
     */
    private int getThreeOfAKindScore() {
        int score = 0;
        HashMap<Integer, Integer> occurences = this.occurrences();

        for (int value : occurences.keySet()) {
            if (occurences.get(value) < 3) {
                // skip if there are less than 3 of value because it's not three of a kind
                continue;
            }

            if (score < value * 3) {
                score = value * 3;
            }
        }

        return score;
    }

    /**
     * Gets score of Four of a Kind that this Collection yields
     * @return the score
     */
    private int getFourOfAKindScore() {
        int score = 0;
        HashMap<Integer, Integer> occurences = this.occurrences();

        for (int value : occurences.keySet()) {
            if (occurences.get(value) < 4) {
                // skip if there are less than 4 of value because it's not four of a kind
                continue;
            }

            if (score < value * 4) {
                score = value * 4;
            }
        }

        return score;
    }

    /**
     * Gets score of Small Straight that this Collection yields
     * @return the score
     */
    private int getSmallStraightScore() {
        for (int value : new int[] { 1, 2, 3, 4, 5 }) {
            if (!this.has(value)) {
                // missing value, meaning it's not a small straight
                return 0;
            }
        }

        // return the score of a small straight
        return 15;
    }

    /**
     * Gets score of Large Straight that this Collection yields
     * @return the score
     */
    private int getLargeStraightScore() {
        for (int value : new int[] { 2, 3, 4, 5, 6 }) {
            if (!this.has(value)) {
                // missing value, meaning it's not a large straight
                return 0;
            }
        }

        // return the score of a large straight
        return 20;
    }

    /**
     * Gets score of Full House that this Collection yields
     * @return the score
     */
    private int getFullHouseScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Chance that this Collection yields
     * @return the score
     */
    private int getChanceScore() {
        return this.getSum();
    }

    /**
     * Gets score of Yahtzee that this Collection yields
     * @return the score
     */
    private int getYatzyScore() {
        if (this.occurrences().keySet().size() != 1) {
            // all dice does not have the same value
            return 0;
        }

        return this.getSum();
    }

    /**
     * Gets a String representation of this class
     * @return a String representation of this class
     */
    @Override
    public String toString() {
        return "YatzyDiceCollection(size:" + this.size() + ")";
    }

    /**
     * Gets an identical new Object of the type class as this
     * @return a new instance identical to this
     */
    @Override
    public YatzyDiceCollection clone() {
        return (YatzyDiceCollection) super.clone();
    }

    /**
     * Gets a String representation of this class
     * @return a String representation of this class
     */
    @Override
    public String toString() {
        return "YatzyDiceCollection(size:" + this.size() + ")";
    }

    /**
     * Gets an identical new Object of the type class as this
     * @return a new instance identical to this
     */
    @Override
    public YatzyDiceCollection clone() {
        return (YatzyDiceCollection) super.clone();
    }
}
