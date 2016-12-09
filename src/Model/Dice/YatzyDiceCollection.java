package Model.Dice;

public class YatzyDiceCollection extends DiceCollection {
    public YatzyDiceCollection() {
        super(5);
    }

    public boolean match(DiceCombination combination) {
        if (combination == DiceCombination.CHANCE) {
            return true;
        }

        return this.getScore(combination) > 0;
    }

    public int getScore(DiceCombination combination) {
//        switch (combination) {
//            case ACES: return this.acesScore();
//            case TWOS: return this.twosScore();
//            case THREES: return this.threesScore();
//            case FOURS: return this.foursScore();
//            case FIVES: return this.fivesScore();
//            case SIXES: return this.sixesScore();
//            case ONE_PAIR: return this.onePairScore();
//            case TWO_PAIRS: return this.twoPairsScore();
//            case THREE_OF_A_KIND: return this.threeOfAKindScore();
//            case FOUR_OF_A_KIND: return this.fourOfAKindScore();
//            case SMALL_STRAIGHT: return this.smallStraightScore();
//            case LARGE_STRAIGHT: return this.largeStraightScore();
//            case FULL_HOUSE: return this.fullHouseScore();
//            case CHANCE: return this.chanceScore();
//            case YATZY: return this.yatzyScore();
//        }

        return 0;
    }

    //private int acesScore() {
    //    // TODO
    //}
    //
    //private int twosScore() {
    //    // TODO
    //}
    //
    //private int threesScore() {
    //    // TODO
    //}
    //
    //private int foursScore() {
    //    // TODO
    //}
    //
    //private int fivesScore() {
    //    // TODO
    //}
    //
    //private int sixesScore() {
    //    // TODO
    //}
    //
    //private int onePairScore() {
    //    // TODO
    //}
    //
    //private int twoPairsScore() {
    //    // TODO
    //}
    //
    //private int threeOfAKindScore() {
    //    // TODO
    //}
    //
    //private int fourOfAKindScore() {
    //    // TODO
    //}
    //
    //private int smallStraightScore() {
    //    // TODO
    //}
    //
    //private int largeStraightScore() {
    //    // TODO
    //}
    //
    //private int fullHouseScore() {
    //    // TODO
    //}

//    private int chanceScore() {
//        // TODO
//    }

    //private int yatzyScore() {
    //    // TODO
    //}
}
