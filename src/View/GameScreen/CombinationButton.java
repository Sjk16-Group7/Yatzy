package View.GameScreen;

import javax.swing.*;

import Model.Dice.DiceCombination;

public class CombinationButton extends JButton {
    private DiceCombination combination;

    public CombinationButton(DiceCombination combination) {
        super();

        this.combination = combination;

        this.setText(this.combination.getName());
    }

    public DiceCombination getCombination() {
        return this.combination;
    }
}
