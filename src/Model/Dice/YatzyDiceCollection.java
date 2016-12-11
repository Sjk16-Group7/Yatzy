package Model.Dice;

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
        // chance is the only exception and is always valid
        if (combination.equals(DiceCombination.CHANCE)) {
            return true;
        }

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
            case ACES: return this.getAcesScore();
            case TWOS: return this.getTwosScore();
            case THREES: return this.getThreesScore();
            case FOURS: return this.getFoursScore();
            case FIVES: return this.getFivesScore();
            case SIXES: return this.getSixesScore();
            case ONE_PAIR: return this.getOnePairScore();
            case TWO_PAIRS: return this.getTwoPairsScore();
            case THREE_OF_A_KIND: return this.getThreeOfAKindScore();
            case FOUR_OF_A_KIND: return this.getFourOfAKindScore();
            case SMALL_STRAIGHT: return this.getSmallStraightScore();
            case LARGE_STRAIGHT: return this.getLargeStraightScore();
            case FULL_HOUSE: return this.getFullHouseScore();
            case CHANCE: return this.getChanceScore();
            case YATZY: return this.getYatzyScore();
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
        // TODO
        return 0;
    }

    /**
     * Gets score of Twos that this Collection yields
     * @return the score
     */
    private int getTwosScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Threes that this Collection yields
     * @return the score
     */
    private int getThreesScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Fours that this Collection yields
     * @return the score
     */
    private int getFoursScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Fives that this Collection yields
     * @return the score
     */
    private int getFivesScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Sixes that this Collection yields
     * @return the score
     */
    private int getSixesScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of One Pair that this Collection yields
     * @return the score
     */
    private int getOnePairScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Two Pairs that this Collection yields
     * @return the score
     */
    private int getTwoPairsScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Three of a Kind that this Collection yields
     * @return the score
     */
    private int getThreeOfAKindScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Four of a Kind that this Collection yields
     * @return the score
     */
    private int getFourOfAKindScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Small Straight that this Collection yields
     * @return the score
     */
    private int getSmallStraightScore() {
        // TODO
        return 0;
    }

    /**
     * Gets score of Large Straight that this Collection yields
     * @return the score
     */
    private int getLargeStraightScore() {
        // TODO
        return 0;
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
        // TODO
        return 0;
    }

    /**
     * Gets score of Yahtzee that this Collection yields
     * @return the score
     */
    private int getYatzyScore() {
        // TODO
        return 0;
    }
}
