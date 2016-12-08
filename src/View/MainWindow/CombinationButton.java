package View.MainWindow;

import Model.Dice.DiceCombination;

import javax.swing.*;

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
