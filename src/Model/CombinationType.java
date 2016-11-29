package Model;

public enum CombinationType {
    ACES("Ettor"), TWOS("Tvåor"), THREES("Treor"), FOURS("Fyror"), FIVES("Femmor"), SIXES("Sexor"),
    ONE_PAIR("Ett par"), TWO_PAIRS("Två par"), THREE_OF_A_KIND("Triss"), FOUR_OF_A_KIND("Fyrtal"),
    SMALL_STRAIGHT("Liten stege"), LARGE_STRAIGHT("Stor stege"), FULL_HOUSE("Kåk"),
    CHANCE("Chans"), YATZY("Yatzy");

    private String name;

    CombinationType(String name) {
        this.name = name;
    }

    String getSwedishName() {
        return this.name;
    }
}