package View.GameScreen;

import java.awt.*;

import javax.swing.*;

import Model.Dice.DiceCombination;

/**
 * CombinationButton is an extension of JButton, customized to have a DiceCombination
 * associated with it.
 * @author Isak
 */
public class CombinationButton extends JButton {
    private DiceCombination combination;

    /**
     * Class constructor specifying the DiceCombination to associate with it
     * @param combination the DiceCombination
     */
    public CombinationButton(DiceCombination combination) {
        super();

        this.combination = combination;
        this.setActionCommand("Combination");
        this.setText(this.combination.getName());

        Dimension size = this.getPreferredSize();
        size.height = 25;
        this.setPreferredSize(size);
    }

    /**
     * Gets the associated DiceCombination
     * @return the DiceCombination
     */
    public DiceCombination getCombination() {
        return this.combination;
    }
}
