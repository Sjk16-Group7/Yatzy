package Model.Dice;

public class YatzyDiceCollection extends DiceCollection {
    public YatzyDiceCollection() {
        super(5);
    }

    public boolean match(DiceCombination combination) {
        if (combination.equals(DiceCombination.CHANCE)) {
            return true;
        }

        return this.getScore(combination) > 0;
    }

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

        return 0;
    }

    private int getAcesScore() {
        // TODO
        return 0;
    }

    private int getTwosScore() {
        // TODO
        return 0;
    }

    private int getThreesScore() {
        // TODO
        return 0;
    }

    private int getFoursScore() {
        // TODO
        return 0;
    }

    private int getFivesScore() {
        // TODO
        return 0;
    }

    private int getSixesScore() {
        // TODO
        return 0;
    }

    private int getOnePairScore() {
        // TODO
        return 0;
    }

    private int getTwoPairsScore() {
        // TODO
        return 0;
    }

    private int getThreeOfAKindScore() {
        // TODO
        return 0;
    }

    private int getFourOfAKindScore() {
        // TODO
        return 0;
    }

    private int getSmallStraightScore() {
        // TODO
        return 0;
    }

    private int getLargeStraightScore() {
        // TODO
        return 0;
    }

    private int getFullHouseScore() {
        // TODO
        return 0;
    }

    private int getChanceScore() {
        // TODO
        return 0;
    }

    private int getYatzyScore() {
        // TODO
        return 0;
    }
}
