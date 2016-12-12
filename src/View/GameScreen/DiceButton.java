package View.GameScreen;

import java.awt.*;

import javax.swing.*;

import Model.Dice.Dice;

/**
 * DiceButton is an extension of JButton, customized to have a Dice associated with it and to
 * be a square with changing graphics.
 * @author Isak
 */
public class DiceButton extends JButton {
    private Dice dice;

    private static ImageIcon[] DICE_IMAGES_NORMAL = new ImageIcon[] {
        new ImageIcon(DiceButton.class.getResource("/Dice1active.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice2active.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice3active.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice4active.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice5active.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice6active.png"))
    };

    private static ImageIcon[] DICE_IMAGES_HOLD = new ImageIcon[] {
        new ImageIcon(DiceButton.class.getResource("/Dice1inactive.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice2inactive.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice3inactive.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice4inactive.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice5inactive.png")),
        new ImageIcon(DiceButton.class.getResource("/Dice6inactive.png"))
    };

    /**
     * Class constructor specifying the Dice to associate with it
     * @param dice the Dice
     */
    public DiceButton(Dice dice) {
        super();

        this.dice = dice;
        this.setActionCommand("Dice");
        this.initAppearance();
    }

    /**
     * Gets the associated Dice
     * @return the Dice
     */
    public Dice getDice() {
        return this.dice;
    }

    /**
     * Updates the graphics of the button. (this needs to be explicitly called to update, there
     * may be a better way to do this...)
     */
    public void update() {
        ImageIcon image_normal = DICE_IMAGES_NORMAL[dice.getValue() - 1];
        ImageIcon image_hold   = DICE_IMAGES_HOLD[dice.getValue() - 1];

        this.setIcon(image_normal);
        this.setDisabledIcon(image_hold);
        this.setSelectedIcon(image_hold);
        this.setDisabledSelectedIcon(image_hold);
        // this.setRolloverIcon(image_hold);
        // this.setRolloverSelectedIcon(image_hold);
    }

    /**
     * Initiates the appearance of this Component
     */
    private void initAppearance() {
        this.addActionListener(event -> this.setSelected(!this.isSelected()));
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setMargin(null);
        this.setPreferredSize(new Dimension(80, 80));

        this.reset();
    }

    /**
     * Resets the appearance of this Component
     */
    public void reset() {
        this.setEnabled(false);
        this.setSelected(false);

        this.setIcon(null);
        this.setDisabledIcon(null);
        this.setSelectedIcon(null);
        this.setDisabledSelectedIcon(null);
    }
}
