package Model.Dice;

/**
 * DiceCombination is an enum with all (supported) kinds of combinations of interest a set of Dice
 * may take during a dice-based game.
 * @author Isak
 */
public enum DiceCombination {
    ACES("Aces"),
    TWOS("Twos"),
    THREES("Threes"),
    FOURS("Fours"),
    FIVES("Fives"),
    SIXES("Sixes"),
    ONE_PAIR("One pair"),
    TWO_PAIRS("Two pairs"),
    THREE_OF_A_KIND("Three of a kind"),
    FOUR_OF_A_KIND("Four of a kind"),
    SMALL_STRAIGHT("Small straight"),
    LARGE_STRAIGHT("Large straight"),
    FULL_HOUSE("Full house"),
    CHANCE("Chance"),
    YATZY("Yatzy");

    private String name;

    /**
     * enum constructor
     * @param name the name of the combination, prettily formatted
     */
    DiceCombination(String name) {
        this.name = name;
    }

    /**
     * Gets a prettily formatted name of this
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}