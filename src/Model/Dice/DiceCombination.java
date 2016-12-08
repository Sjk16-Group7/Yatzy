package Model.Dice;

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

    DiceCombination(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}