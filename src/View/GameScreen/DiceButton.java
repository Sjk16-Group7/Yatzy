package View.GameScreen;

import java.awt.*;

import javax.swing.*;

import Model.Dice.Dice;

public class DiceButton extends JButton {
    private Dice dice;

    private static ImageIcon[] DICE_IMAGES = new ImageIcon[] {
            new ImageIcon("src/Images/Dice1.png"),
            new ImageIcon("src/Images/Dice2.png"),
            new ImageIcon("src/Images/Dice3.png"),
            new ImageIcon("src/Images/Dice4.png"),
            new ImageIcon("src/Images/Dice5.png"),
            new ImageIcon("src/Images/Dice6.png")
    };

    public DiceButton(Dice dice) {
        super();

        this.dice = dice;

        this.initAppearance();
    }

    public Dice getDice() {
        return this.dice;
    }

    public void update() {
        ImageIcon image = DICE_IMAGES[dice.getValue() - 1];
        this.setIcon(image);
    }

    private void initAppearance() {
        this.update();

        // TODO Colors etc.
        int size = this.getIcon().getIconWidth();

        this.setBorder(BorderFactory.createEmptyBorder());

        this.setSize(size, size);
        this.setPreferredSize(new Dimension(size, size));
    }

}
